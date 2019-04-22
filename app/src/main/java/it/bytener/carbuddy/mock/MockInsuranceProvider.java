package it.bytener.carbuddy.mock;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.dagger.MockedProviderModule;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.Insurance;
import it.bytener.carbuddy.room.providers.InsuranceProvider;

@PerFragment
public class MockInsuranceProvider implements IInsuranceProvider {

    @Inject
    public MockInsuranceProvider(){

    }

    @Override
    public LiveData<List<Insurance>> getInsurances(long vehicleId) {
        return null;
    }

    @Override
    public void insertInsurance(Insurance insurance, IBackgroundOperationResponse response) {

    }
}
