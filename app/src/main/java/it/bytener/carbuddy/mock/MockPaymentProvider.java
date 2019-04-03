package it.bytener.carbuddy.mock;

import java.util.List;
import java.util.Random;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.room.entities.Payment;

public class MockPaymentProvider implements IPaymentProvider {
    List<Payment> payments;
    List<Payment> paymentsAvailable;
    MutableLiveData<List<Payment>> mutablePayments;
    Random random = new Random();

    @Override
    public LiveData<List<Payment>> getPayments(long vehicleId) {
        return mutablePayments;
    }

    @Override
    public void insertPayment(Payment payment, IBackgroundOperationResponse response) {
        if(payment == null){
            int rnd = random.nextInt(paymentsAvailable.size());
            payment = paymentsAvailable.get(rnd);
            response.getResponse(rnd);
        }
        payments.add(payment);
        mutablePayments.setValue(payments);
    }
}
