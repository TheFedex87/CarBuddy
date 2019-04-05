package it.bytener.carbuddy.room.providers;

import java.util.List;
import java.util.function.Predicate;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.executors.AppExecutors;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.Vehicle;

public class VehicleProvider implements IVehicleProvider {
    private AppDatabase db;

    public VehicleProvider(AppDatabase db){
        this.db = db;
    }

    @Override
    public LiveData<List<Vehicle>> getVehicles() {
        return db.vehicleDao().getVehicles();
    }

    public void insertVehicle(final Vehicle vehicle, IBackgroundOperationResponse response){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                long newId = db.vehicleDao().insertVehicle(vehicle);
                response.getResponse(newId, vehicle);
            }
        });
    }
}
