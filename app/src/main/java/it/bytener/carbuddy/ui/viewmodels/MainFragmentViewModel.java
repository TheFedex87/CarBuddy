package it.bytener.carbuddy.ui.viewmodels;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.Vehicle;

public class MainFragmentViewModel extends ViewModel {
    private IVehicleProvider vehicleProvider;

    public MainFragmentViewModel(IVehicleProvider vehicleProvider){
        this.vehicleProvider = vehicleProvider;
    }

    public LiveData<List<Vehicle>> getVehicles() {
        return vehicleProvider.getVehicles();
    }
}
