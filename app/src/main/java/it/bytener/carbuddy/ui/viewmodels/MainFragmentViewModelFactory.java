package it.bytener.carbuddy.ui.viewmodels;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.room.AppDatabase;

public class MainFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final IVehicleProvider vehicleProvider;
    private final IPaymentProvider paymentProvider;
    private final IBackgroundOperationResponse response;

    @Inject
    public MainFragmentViewModelFactory(IVehicleProvider vehicleProvider, IPaymentProvider paymentProvider, IBackgroundOperationResponse response){
        this.vehicleProvider = vehicleProvider;
        this.paymentProvider = paymentProvider;
        this.response = response;
    }

    public <T extends ViewModel>T create(Class<T> modelClass){
        return (T) new MainFragmentViewModel(vehicleProvider, paymentProvider, response);
    }
}
