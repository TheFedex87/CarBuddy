package it.bytener.carbuddy.room.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.Vehicle;

@Dao
public interface VehicleDao {
    @Query("SELECT * FROM vehicle")
    LiveData<List<Vehicle>> getVehicles();

    @Insert
    long insertVehicle(Vehicle vehicle);
}
