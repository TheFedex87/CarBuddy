package it.bytener.carbuddy.room.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import it.bytener.carbuddy.room.entities.Insurance;

@Dao
public interface CarTaxDao {
    @Query("SELECT * FROM car")
    LiveData<List<Insurance>> getInsurances();

    @Query("SELECT * FROM insurance WHERE insurance.vechicleId = :vehicleId")
    LiveData<List<Insurance>> getVehicleInsurances(long vehicleId);

    @Insert
    long insertInsurance(Insurance insurance);
}
