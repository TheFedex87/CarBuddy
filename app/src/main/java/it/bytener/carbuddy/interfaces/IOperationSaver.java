package it.bytener.carbuddy.interfaces;

import it.bytener.carbuddy.dagger.AddPaymentFragmentComponent;

public interface IOperationSaver {
    void saveOperation(AddPaymentFragmentComponent addPaymentFragmentComponent, long vehicleId);
}
