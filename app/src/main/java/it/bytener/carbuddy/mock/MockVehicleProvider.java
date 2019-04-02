package it.bytener.carbuddy.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.Vehicle;

public class MockVehicleProvider implements IVehicleProvider {
    List<Vehicle> vehicles;
    List<Vehicle> vehiclesAvailable;
    MutableLiveData<List<Vehicle>> mutableVehicles;
    Random random = new Random();

    public MockVehicleProvider(){
        mutableVehicles = new MutableLiveData<>();
        vehicles = new ArrayList();

        vehiclesAvailable = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Mercedes");
        vehicle.setModel("Classe A");
        vehiclesAvailable.add(vehicle);
        vehicle = new Vehicle();
        vehicle.setBrand("Opel");
        vehicle.setModel("Astra");
        vehiclesAvailable.add(vehicle);
        vehicle = new Vehicle();
        vehicle.setBrand("BMW");
        vehicle.setModel("Serie 1");
        vehiclesAvailable.add(vehicle);
    }

    @Override
    public LiveData<List<Vehicle>> getVehicles() {
        return mutableVehicles;
    }

    @Override
    public void insertVehicle(Vehicle vehicle) {
        if(vehicle == null){
            int rnd = random.nextInt(vehiclesAvailable.size());
            vehicle = vehiclesAvailable.get(rnd);
        }
        vehicles.add(vehicle);

        mutableVehicles.setValue(vehicles);
    }
}
