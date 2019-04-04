package it.bytener.carbuddy.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.application.CarBuddyApplication;
import it.bytener.carbuddy.dagger.ApplicationModule;
import it.bytener.carbuddy.dagger.DaggerViewModelComponent;
import it.bytener.carbuddy.dagger.ViewModelModule;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.Vehicle;
import it.bytener.carbuddy.ui.adapters.VehiclePagerAdapter;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModel;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

public class MainFragment extends Fragment implements IBackgroundOperationResponse {
    private Context context;
    private VehiclePagerAdapter vehiclePagerAdapter;
    private List<IVehicle> vehicleList;

    private MainFragmentViewModel mainFragmentViewModel;

    private MainFragmentViewModelFactory mainFragmentViewModelFactory;

    @BindView(R.id.button_add_vehicle)
    Button addVehicleButton;
    @BindView(R.id.vehicles_photo_pager)
    ViewPager vehiclesPager;
    @BindView(R.id.next_reminders_recycler_view)
    RecyclerView nextRemindersRecyclerView;

    public MainFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        CarBuddyApplication.appComponent().inject(this);

        mainFragmentViewModelFactory = DaggerViewModelComponent
                .builder()
                .viewModelModule(new ViewModelModule(this))
                .applicationModule(new ApplicationModule(context))
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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        addVehicleButton.setOnClickListener(v -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setModel("Serie 1");
            vehicle.setBrand("BMW");
            vehicle = null;
            mainFragmentViewModel.setVehicle(vehicle);
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
        });
    }

    @Override
    public void getResponse(long r) {
        Snackbar.make(getActivity().findViewById(R.id.main_layout), String.valueOf(r), Snackbar.LENGTH_LONG).show();
    }
}
