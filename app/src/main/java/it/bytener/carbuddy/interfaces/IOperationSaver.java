package it.bytener.carbuddy.interfaces;

import it.bytener.carbuddy.dagger.ViewModelComponent;

public interface IOperationSaver {
    void saveOperation(ViewModelComponent viewModelComponent, long vehicleId);
}
