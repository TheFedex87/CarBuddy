package it.bytener.carbuddy.room.providers;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.Insurance;
import it.bytener.carbuddy.room.entities.Payment;

@PerFragment
public class InsuranceProvider implements IInsuranceProvider {
    private AppDatabase db;

    @Inject
    public InsuranceProvider(AppDatabase db){
        this.db = db;
    }

    @Override
    public LiveData<List<Insurance>> getInsurances(long vehicleId) {
        return null;
    }

    @Override
    public void insertInsurance(Insurance payment, IBackgroundOperationResponse response) {

    }
}
