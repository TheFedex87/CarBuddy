package it.bytener.carbuddy.interfaces;

import java.util.List;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.room.entities.Payment;

public interface IPaymentProvider {
    LiveData<List<Payment>> getPayments(long vehicleId);
    void insertPayment(Payment payment, IBackgroundOperationResponse response);
}
