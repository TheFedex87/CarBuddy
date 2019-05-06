package it.bytener.carbuddy.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.CarTax;
import it.bytener.carbuddy.room.providers.CarTaxProvider;

@Singleton
public class MockCarTaxProvider implements ICarTaxProvider {
    private MutableLiveData<List<CarTax>> carTaxes;

    private List<CarTax> carTaxesAvailable;
    private List<CarTax> currentCarTaxList;

    private Random rnd;

    @Inject
    public MockCarTaxProvider(){
        rnd = new Random();
        carTaxes = new MutableLiveData<>();
        currentCarTaxList = new ArrayList<>();

        carTaxesAvailable = new ArrayList<>();
        CarTax carTax = new CarTax();
        carTax.setId(1);
        carTax.setNote("Bla bla bla");
        carTax.setPaymentDate(156151651);
        carTaxesAvailable.add(carTax);

        carTax = new CarTax();
        carTax.setId(2);
        carTax.setNote("Bla bla bla");
        carTax.setPaymentDate(14141414);
        carTaxesAvailable.add(carTax);

        carTax = new CarTax();
        carTax.setId(3);
        carTax.setNote("Bla bla bla");
        carTax.setPaymentDate(17171717);
        carTaxesAvailable.add(carTax);
    }

    @Override
    public LiveData<List<CarTax>> getCarTaxes(long vehicleId) {
        List<CarTax> filteredCarTax = new ArrayList<>();
        carTaxes.postValue(filteredCarTax);
        for (CarTax carTax:currentCarTaxList) {
            if(carTax.getVehicleId() == vehicleId)
                filteredCarTax.add(carTax);
        }
        carTaxes.postValue(filteredCarTax);
        return carTaxes;
    }

    @Override
    public void insertCarTax(CarTax carTax, IBackgroundOperationResponse response) {
        int ind = rnd.nextInt(carTaxesAvailable.size());
        CarTax carTax2 = new CarTax();
        carTax2.setVehicleId(carTax.getVehicleId());
        carTax2.setPaymentDate(carTaxesAvailable.get(ind).getPaymentDate());
        carTax2.setNote(carTaxesAvailable.get(ind).getNote());
        carTax2.setId(carTaxesAvailable.get(ind).getId());
        currentCarTaxList.add(carTax2);
        //carTaxes.postValue(currentCarTaxList);
    }
}
