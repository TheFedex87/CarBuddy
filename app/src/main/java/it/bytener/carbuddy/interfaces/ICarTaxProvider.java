package it.bytener.carbuddy.interfaces;

import java.util.List;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.room.entities.CarTax;
import it.bytener.carbuddy.room.entities.Insurance;

public interface ICarTaxProvider {
    LiveData<List<CarTax>> getCarTaxes(long vehicleId);

    void insertCarTax(CarTax carTax, IBackgroundOperationResponse response);
}
