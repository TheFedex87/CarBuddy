package it.bytener.carbuddy.ui.viewmodels;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.Vehicle;

public class MainFragmentViewModel extends ViewModel {
    private IVehicleProvider vehicleProvider;
    private IBackgroundOperationResponse response;

    public MainFragmentViewModel(IVehicleProvider vehicleProvider, IBackgroundOperationResponse response){
        this.vehicleProvider = vehicleProvider;
        this.response = response;
    }

    public LiveData<List<Vehicle>> getVehicles() {
        return vehicleProvider.getVehicles();
    }

    public void setVehicle(Vehicle vehicle){
        vehicleProvider.insertVehicle(vehicle, response);
    }
}
