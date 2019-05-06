package it.bytener.carbuddy.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.room.entities.Vehicle;

@Singleton
public class MockVehicleProvider implements IVehicleProvider {
    List<Vehicle> vehicles;
    List<Vehicle> vehiclesAvailable;
    MutableLiveData<List<Vehicle>> mutableVehicles;
    Random random = new Random();

    private int currentIndex = 0;

    @Inject
    public MockVehicleProvider(){
        mutableVehicles = new MutableLiveData<>();
        vehicles = new ArrayList();

        vehiclesAvailable = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Mercedes");
        vehicle.setModel("Classe A");
        vehicle.setId(1);
        vehiclesAvailable.add(vehicle);
        vehicle = new Vehicle();
        vehicle.setBrand("Opel");
        vehicle.setModel("Astra");
        vehicle.setId(2);
        vehiclesAvailable.add(vehicle);
        vehicle = new Vehicle();
        vehicle.setBrand("BMW");
        vehicle.setModel("Serie 1");
        vehicle.setId(3);
        vehiclesAvailable.add(vehicle);
    }

    @Override
    public LiveData<List<Vehicle>> getVehicles() {
        return mutableVehicles;
    }

    @Override
    public void insertVehicle(Vehicle vehicle, IBackgroundOperationResponse response) {
        if(currentIndex < 3) {
            //if(vehicle == null){
            int rnd = random.nextInt(vehiclesAvailable.size());
            vehicle = vehiclesAvailable.get(currentIndex);
            response.getResponse(currentIndex, vehicle);
            //}
            vehicles.add(vehicle);
            mutableVehicles.setValue(vehicles);
            currentIndex++;
        }
    }
}
