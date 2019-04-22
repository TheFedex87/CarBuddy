package it.bytener.carbuddy.room.providers;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.executors.AppExecutors;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.Payment;

/*@PerFragment
public class PaymentProvider implements IPaymentProvider {
    private AppDatabase db;

    @Inject
    public PaymentProvider(AppDatabase db){
        this.db = db;
    }

    @Override
    public LiveData<List<Payment>> getPayments(long vehicleId) {
        return db.paymentDao().paymentOfVehicle(vehicleId);
    }

    //@Override
    //public LiveData<List<Payment>> getAllPayments(){
    //    return db.paymentDao().allPayments();
    //}

    @Override
    public void insertPayment(Payment payment, IBackgroundOperationResponse response) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                long newId = db.paymentDao().insertPayment(payment);
                response.getResponse(newId, payment);
            }
        });
    }
}*/
