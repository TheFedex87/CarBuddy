package it.bytener.carbuddy.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.application.CarBuddyApplication;
import it.bytener.carbuddy.dagger.DaggerViewModelComponent;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.Vehicle;
import it.bytener.carbuddy.ui.adapters.VehiclePagerAdapter;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModel;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

public class MainFragment extends Fragment {

    @Inject
    Context context;

    @Inject
    IVehicleProvider vehicleProvider;

    @Inject
    MainFragmentViewModelFactory mainFragmentViewModelFactory;

    private ViewPager vehiclesPager;
    private VehiclePagerAdapter vehiclePagerAdapter;
    private List<IVehicle> vehicleList;

    public MainFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CarBuddyApplication.appComponent().inject(this);

        setupViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        vehiclesPager = rootView.findViewById(R.id.vehicles_adapter);

        vehiclePagerAdapter = new VehiclePagerAdapter(context, vehicleList);
        vehiclesPager.setAdapter(vehiclePagerAdapter);

        final Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Mercedes");
        vehicle.setModel("Classe A");
        vehicle.setCylinderSize(1500);
        vehicle.setHorsepower(110);
        rootView.findViewById(R.id.button_add_vehicle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicleProvider.insertVehicle(vehicle);
            }
        });

        return rootView;
    }

    private void setupViewModel(){
        MainFragmentViewModel mainFragmentViewModel = ViewModelProviders.of(this, mainFragmentViewModelFactory).get(MainFragmentViewModel.class);
        mainFragmentViewModel.getVehicles().observe(this, new Observer<List<Vehicle>>() {
            @Override
            public void onChanged(List<Vehicle> vehicles) {
                List<IVehicle> iVehicles = new ArrayList<IVehicle>(vehicles);
                if(vehiclePagerAdapter != null){
                    vehiclePagerAdapter.swapVehicles(iVehicles);
                }
                vehicleList = iVehicles;
            }
        });
    }
}
