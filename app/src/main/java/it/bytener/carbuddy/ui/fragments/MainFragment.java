package it.bytener.carbuddy.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.application.CarBuddyApplication;
import it.bytener.carbuddy.dagger.DaggerMainFragmentComponent;
import it.bytener.carbuddy.dagger.MainFragmentComponent;
import it.bytener.carbuddy.dagger.UserInterfaceModule;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.models.IReminder;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.CarTax;
import it.bytener.carbuddy.room.entities.Insurance;
import it.bytener.carbuddy.room.entities.Vehicle;
import it.bytener.carbuddy.ui.NavigationDrawerHeaderViewHolder;
import it.bytener.carbuddy.ui.adapters.ReminderAdapter;
import it.bytener.carbuddy.ui.adapters.VehiclePagerAdapter;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModel;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

public class MainFragment extends Fragment implements IBackgroundOperationResponse {
    private boolean firstLoadDone = false;

    private Context context;
    @Inject
    public VehiclePagerAdapter vehiclePagerAdapter;
    private List<IVehicle> vehicleList;

    @Inject
    public ReminderAdapter reminderAdapter;
    private List<IReminder> reminderList;

    private List<Insurance> insuranceList;
    private List<CarTax> carTaxList;

    @Inject
    public NavigationDrawerHeaderViewHolder navigationDrawerHeaderViewHolder;

    //private AddPaymentFragmentComponent viewModelComponent;
    private MainFragmentComponent mainFragmentComponent;

    private MainFragmentViewModel mainFragmentViewModel;
    @Inject
    MainFragmentViewModelFactory mainFragmentViewModelFactory;

    @BindView(R.id.button_add_vehicle)
    Button addVehicleButton;
    /*@BindView(R.id.button_add_payment)
    Button addPaymentButton;*/
    @BindView(R.id.vehicles_photo_pager)
    ViewPager vehiclesPager;
    @BindView(R.id.next_reminders_recycler_view)
    RecyclerView nextRemindersRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_container)
    CollapsingToolbarLayout collapsingToolbarLayout;

    //NavigationDrawer
    //@BindView(R.id.nav_view)
    //NavigationView navigationDrawer;

    @BindView(R.id.add_operation_fab)
    FloatingActionButton addPaymentFab;

    private final SharedPreferences sharedPreferences = CarBuddyApplication.appComponent().getSharedPreferences();

    private Random rnd = new Random();

    public MainFragment(){

    }

    private final static String TAG = MainFragment.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        //CarBuddyApplication.appComponent().inject(this);


        /*viewModelComponent = DaggerAddPaymentFragmentComponent
                .builder()
                .applicationComponent(CarBuddyApplication.appComponent())
                .response(this)
                .build();*/

        //setupViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //vehiclePagerAdapter = new VehiclePagerAdapter(context, vehicleList);
        if(vehicleList == null) vehicleList = new ArrayList<>();
        if(reminderList == null) reminderList = new ArrayList<>();
        mainFragmentComponent = DaggerMainFragmentComponent
                .builder()
                .applicationComponent(CarBuddyApplication.appComponent())
                .userInterfaceModule(new UserInterfaceModule(LinearLayoutManager.VERTICAL))
                .vehicleList(vehicleList)
                .reminderList(reminderList)
                .headerView(((NavigationView)(getActivity().findViewById(R.id.nav_view))).getHeaderView(0))
                .response(this)
                .build();

        mainFragmentComponent.inject(this);

        vehiclesPager.setAdapter(vehiclePagerAdapter);
        vehiclesPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainFragmentViewModel.setVehicleId(vehicleList.get(position).getId());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("vehicle_index", position);
                editor.apply();

                bindNavigationViewVehicle(vehicleList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if(sharedPreferences.contains("vehicle_index")){
            vehiclesPager.post(new Runnable() {
                @Override
                public void run() {
                    int vehicleIndex = sharedPreferences.getInt("vehicle_index", 0);
                    if(vehicleIndex < vehicleList.size()) {
                        vehiclesPager.setCurrentItem(vehicleIndex);

                        bindNavigationViewVehicle(vehicleList.get(vehicleIndex));

                    }
                }
            });
        }


        //reminderAdapter = new ReminderAdapter(reminderList);
        nextRemindersRecyclerView.setAdapter(reminderAdapter);
        nextRemindersRecyclerView.setLayoutManager(mainFragmentComponent.getLinearLayoutManager());

        addVehicleButton.setOnClickListener(v -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setModel("Serie 1");
            vehicle.setBrand("BMW");
            mainFragmentViewModel.setVehicle(vehicle);
        });

        /*addPaymentButton.setOnClickListener(v -> {
            if(sharedPreferences.contains("vehicle_index")) {
                long vehicleIndex = vehicleList.get(sharedPreferences.getInt("vehicle_index", 0)).getId();
                CarTax carTax = new CarTax();
                carTax.setNote("Nota pagamentooo");
                carTax.setVehicleId(vehicleIndex);
                mainFragmentViewModel.setCarTax(carTax);
            }
            //Payment payment = new Payment();
            //payment.setDescription("Bollo");
            //payment.setVehicleId(rnd.nextInt(5));
            //mainFragmentViewModel.setPayment(payment);
        });*/

        addPaymentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vehicleIndex = sharedPreferences.getInt("vehicle_index", 0);
                MainFragmentDirections.ActionMainFragmentToAddPaymentFragment action = MainFragmentDirections.actionMainFragmentToAddPaymentFragment();
                action.setVehicleId(vehicleList.get(vehicleIndex).getId());
                Navigation.findNavController(v).navigate(action);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupViewModel();
    }

    private void setupViewModel(){
        //mainFragmentViewModelFactory = viewModelComponent.getMainFragmentViewModelFactory();
        mainFragmentViewModel = ViewModelProviders.of(this, mainFragmentViewModelFactory).get(MainFragmentViewModel.class);

        mainFragmentViewModel.getVehicles().observe(getViewLifecycleOwner(), vehicles -> {
            List<IVehicle> iVehicles = new ArrayList<IVehicle>(vehicles);
            if(vehiclePagerAdapter != null){
                vehiclePagerAdapter.swapVehicles(iVehicles);
            }
            vehicleList = iVehicles;

            if(vehicleList.size() > 0 && !firstLoadDone) {
                mainFragmentViewModel.setVehicleId(vehicleList.get(0).getId());
                firstLoadDone = true;

                bindNavigationViewVehicle(vehicleList.get(0));
            }

        });

        /*mainFragmentViewModel.getPayments().observe(this, new Observer<List<Payment>>() {
            @Override
            public void onChanged(List<Payment> payments) {
                List<IReminder> iPayments = new ArrayList<IReminder>(payments);
                if(reminderAdapter != null){
                    reminderAdapter.swapReminders(iPayments);
                }
            }
        });*/
        mainFragmentViewModel.getCarTaxes().observe(getViewLifecycleOwner(), new Observer<List<CarTax>>() {
            @Override
            public void onChanged(List<CarTax> carTaxes) {
                carTaxList = carTaxes;
                regenerateReminderList();
            }
        });
        mainFragmentViewModel.getInsurances().observe(getViewLifecycleOwner(), new Observer<List<Insurance>>() {
            @Override
            public void onChanged(List<Insurance> insurances) {
                insuranceList = insurances;
                regenerateReminderList();
            }
        });

    }

    @Override
    public void getResponse(long r, Object sender) {
        Snackbar.make(getActivity().findViewById(R.id.drawer_layout), String.valueOf(r), Snackbar.LENGTH_LONG).show();
    }

    private void regenerateReminderList(){
        reminderList = new ArrayList<>();
        if(insuranceList != null) reminderList.addAll(insuranceList);
        if(carTaxList != null) reminderList.addAll(carTaxList);
        if(reminderAdapter != null){
            reminderAdapter.swapReminders(reminderList);
        }
    }

    private void bindNavigationViewVehicle(IVehicle vehicle){
        navigationDrawerHeaderViewHolder.headerVehicleBrande.setText(String.valueOf(vehicle.getBrand()));
        navigationDrawerHeaderViewHolder.headerVehicleName.setText(String.valueOf(vehicle.getModel()));

        collapsingToolbarLayout.setTitle(vehicle.getBrand() + " " + vehicle.getModel());
    }
}
