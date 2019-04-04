package it.bytener.carbuddy.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

@Module
public class ViewModelModule {
    private IBackgroundOperationResponse response;

    public ViewModelModule(IBackgroundOperationResponse response){
        this.response = response;
    }

    @Singleton
    @Provides
    public MainFragmentViewModelFactory provideMainFragmentViewModelFactory(IVehicleProvider vehicleProvider, IPaymentProvider paymentProvider){
        return new MainFragmentViewModelFactory(vehicleProvider, paymentProvider, response);
    }
}
