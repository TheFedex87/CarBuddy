package it.bytener.carbuddy.room.providers;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.CarTax;
import it.bytener.carbuddy.room.entities.Payment;

@PerFragment
public class CarTaxProvider implements ICarTaxProvider {
    private AppDatabase db;

    @Inject
    public CarTaxProvider(AppDatabase db){
        this.db = db;
    }

    @Override
    public LiveData<List<CarTax>> getCarTaxes(long vehicleId) {
        return null;
    }

    @Override
    public void insertCarTax(CarTax payment, IBackgroundOperationResponse response) {

    }
}
