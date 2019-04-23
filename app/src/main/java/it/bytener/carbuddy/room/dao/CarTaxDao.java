package it.bytener.carbuddy.room.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import it.bytener.carbuddy.room.entities.CarTax;
import it.bytener.carbuddy.room.entities.Insurance;

@Dao
public interface CarTaxDao {
    @Query("SELECT * FROM cartax")
    LiveData<List<CarTax>> getCarTaxes();

    @Query("SELECT * FROM cartax WHERE vehicleId = :vehicleId")
    LiveData<List<CarTax>> getVehicleCarTaxes(long vehicleId);

    @Insert
    long insertCarTax(CarTax carTax);
}
