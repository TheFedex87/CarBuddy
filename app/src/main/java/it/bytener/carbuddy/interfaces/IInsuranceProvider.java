package it.bytener.carbuddy.interfaces;

import java.util.List;

import androidx.lifecycle.LiveData;
import it.bytener.carbuddy.room.entities.Insurance;
import it.bytener.carbuddy.room.entities.Payment;

public interface IInsuranceProvider {
    LiveData<List<Insurance>> getInsurances(long vehicleId);

    void insertInsurance(Insurance insurance, IBackgroundOperationResponse response);
}
