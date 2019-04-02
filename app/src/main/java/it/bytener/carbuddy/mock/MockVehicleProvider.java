package it.bytener.carbuddy.mock;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.Vehicle;

public class MockVehicleProvider implements IVehicleProvider {
    List<Vehicle> vehicles;
    MutableLiveData<List<Vehicle>> mutableVehicles;

    public MockVehicleProvider(){
        mutableVehicles = new MutableLiveData<>();
        vehicles = new ArrayList();
    }

    @Override
    public LiveData<List<Vehicle>> getVehicles() {
        return mutableVehicles;
    }

    @Override
    public void insertVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);

        mutableVehicles.setValue(vehicles);
    }
}
