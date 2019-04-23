package it.bytener.carbuddy.room.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import it.bytener.carbuddy.room.entities.CarTax;
import it.bytener.carbuddy.room.entities.Insurance;

@Dao
public interface InsuranceDao {
    @Query("SELECT * FROM insurance")
    LiveData<List<Insurance>> getInsurances();

    @Query("SELECT * FROM insurance WHERE vehicleId = :vehicleId")
    LiveData<List<Insurance>> getVehicleInsurances(long vehicleId);

    @Insert
    long insertInsurance(Insurance insurance);
}
