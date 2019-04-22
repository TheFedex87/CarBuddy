package it.bytener.carbuddy.room.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import it.bytener.carbuddy.room.entities.CarTax;

@Dao
public interface InsuranceDao {
    @Query("SELECT * FROM cartax")
    LiveData<List<CarTax>> getCarTaxes();

    @Query("SELECT * FROM cartax WHERE cartax.vehicleId = :vehicleId")
    LiveData<List<CarTax>> getVehicleCarTaxes(long vehicleId);

    @Insert
    long insertCarTax(CarTax carTax);
}
