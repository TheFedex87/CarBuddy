package it.bytener.carbuddy.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.application.CarBuddyApplication;
import it.bytener.carbuddy.dagger.DaggerViewModelComponent;
import it.bytener.carbuddy.dagger.ViewModelComponent;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.models.IReminder;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.Payment;
import it.bytener.carbuddy.room.entities.Vehicle;
import it.bytener.carbuddy.ui.adapters.ReminderAdapter;
import it.bytener.carbuddy.ui.adapters.VehiclePagerAdapter;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModel;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

public class MainFragment extends Fragment implements IBackgroundOperationResponse {
    private boolean firstLoadDone = false;

    private Context context;
    private VehiclePagerAdapter vehiclePagerAdapter;
    private List<IVehicle> vehicleList;

    private ReminderAdapter reminderAdapter;
    private List<IReminder> reminderList;

    private MainFragmentViewModel mainFragmentViewModel;

    private MainFragmentViewModelFactory mainFragmentViewModelFactory;

    @BindView(R.id.button_add_vehicle)
    Button addVehicleButton;
    @BindView(R.id.button_add_payment)
    Button addPaymentButton;
    @BindView(R.id.vehicles_photo_pager)
    ViewPager vehiclesPager;
    @BindView(R.id.next_reminders_recycler_view)
    RecyclerView nextRemindersRecyclerView;

    Random rnd = new Random();

    public MainFragment(){

    }

    private final static String TAG = MainFragment.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        //CarBuddyApplication.appComponent().inject(this);

        mainFragmentViewModelFactory = DaggerViewModelComponent
                .builder()
                .applicationComponent(CarBuddyApplication.appComponent())
                .response(this)
                .build()
                .getMainFragmentViewModelFactory();

        setupViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        vehiclePagerAdapter = new VehiclePagerAdapter(context, vehicleList);
        vehiclesPager.setAdapter(vehiclePagerAdapter);
        vehiclesPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainFragmentViewModel.setPaymentVehicleId(vehicleList.get(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        reminderAdapter = new ReminderAdapter(reminderList);
        nextRemindersRecyclerView.setAdapter(reminderAdapter);
        nextRemindersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        addVehicleButton.setOnClickListener(v -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setModel("Serie 1");
            vehicle.setBrand("BMW");
            mainFragmentViewModel.setVehicle(vehicle);
        });

        addPaymentButton.setOnClickListener(v -> {
            Payment payment = new Payment();
            payment.setDescription("Bollo");
            payment.setVehicleId(rnd.nextInt(5));
            mainFragmentViewModel.setPayment(payment);
        });

        return rootView;
    }

    private void setupViewModel(){
        mainFragmentViewModel = ViewModelProviders.of(this, mainFragmentViewModelFactory).get(MainFragmentViewModel.class);

        mainFragmentViewModel.getVehicles().observe(this, vehicles -> {
            List<IVehicle> iVehicles = new ArrayList<IVehicle>(vehicles);
            if(vehiclePagerAdapter != null){
                vehiclePagerAdapter.swapVehicles(iVehicles);
            }
            vehicleList = iVehicles;

            if(vehicleList.size() > 0 && !firstLoadDone) {
                mainFragmentViewModel.setPaymentVehicleId(vehicleList.get(0).getId());
                firstLoadDone = true;
            }

        });

        mainFragmentViewModel.getPayments().observe(this, new Observer<List<Payment>>() {
            @Override
            public void onChanged(List<Payment> payments) {
                List<IReminder> iPayments = new ArrayList<IReminder>(payments);
                if(reminderAdapter != null){
                    reminderAdapter.swapReminders(iPayments);
                }
            }
        });

    }

    @Override
    public void getResponse(long r, Object sender) {
        Snackbar.make(getActivity().findViewById(R.id.main_layout), String.valueOf(r), Snackbar.LENGTH_LONG).show();
    }
}
