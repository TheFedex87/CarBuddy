package it.bytener.carbuddy.ui.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.room.AppDatabase;

public class MainFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final IVehicleProvider vehicleProvider;
    private IBackgroundOperationResponse response;

    public MainFragmentViewModelFactory(IVehicleProvider vehicleProvider, IBackgroundOperationResponse response){
        this.vehicleProvider = vehicleProvider;
        this.response = response;
    }

    public <T extends ViewModel>T create(Class<T> modelClass){
        return (T) new MainFragmentViewModel(vehicleProvider, response);
    }
}
