package it.bytener.carbuddy.room.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import it.bytener.carbuddy.room.entities.Payment;

@Dao
public interface PaymentDao {
    @Query("SELECT * FROM payment WHERE vehicleId = :vehicleId")
    LiveData<List<Payment>> paymentOfVehicle(long vehicleId);

    @Insert
    long insertPayment(Payment vehicle);
}
