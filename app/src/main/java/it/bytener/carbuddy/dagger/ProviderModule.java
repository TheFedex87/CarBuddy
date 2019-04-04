package it.bytener.carbuddy.dagger;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.mock.MockPaymentProvider;
import it.bytener.carbuddy.mock.MockVehicleProvider;
import it.bytener.carbuddy.room.AppDatabase;

@Module
public class ProviderModule {

    @Singleton
    @Provides
    public IVehicleProvider provideVehicleProvider(AppDatabase db){
        //return new VehicleProvider(db);
        return new MockVehicleProvider();
    }

    @Singleton
    @Provides
    public IPaymentProvider providePaymentProvider(AppDatabase db){
        return new MockPaymentProvider();
    }
}
