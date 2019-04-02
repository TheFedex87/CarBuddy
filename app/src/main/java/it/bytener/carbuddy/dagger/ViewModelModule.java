package it.bytener.carbuddy.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

@Module
public class ViewModelModule {

    @Singleton
    @Provides
    public MainFragmentViewModelFactory provideMainFragmentViewModelFactory(IVehicleProvider vehicleProvider){
        return new MainFragmentViewModelFactory(vehicleProvider);
    }
}
