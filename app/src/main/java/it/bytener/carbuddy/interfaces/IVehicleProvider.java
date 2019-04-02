package it.bytener.carbuddy.interfaces;

import java.util.List;
import java.util.function.Predicate;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.Vehicle;

public interface IVehicleProvider {
    LiveData<List<Vehicle>> getVehicles();
    void insertVehicle(Vehicle vehicle, IBackgroundOperationResponse response);
}
