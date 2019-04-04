package it.bytener.carbuddy.dagger;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.mock.MockPaymentProvider;
import it.bytener.carbuddy.mock.MockVehicleProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.providers.PaymentProvider;
import it.bytener.carbuddy.room.providers.VehicleProvider;

@Module
public class ProviderModule {

    @Singleton
    @Provides
    public IVehicleProvider provideVehicleProvider(AppDatabase db){
        return new VehicleProvider(db);
    }

    @Singleton
    @Provides
    public IPaymentProvider providePaymentProvider(AppDatabase db){
        return new PaymentProvider(db);
    }
}
