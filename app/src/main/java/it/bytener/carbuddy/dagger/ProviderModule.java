package it.bytener.carbuddy.dagger;


import javax.inject.Singleton;

import dagger.Binds;
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
public abstract class ProviderModule {

    @Singleton
    @Binds
    abstract IVehicleProvider bindVehicleProvider(VehicleProvider vehicleProvider);

    @Singleton
    @Binds
    abstract IPaymentProvider bindPaymentProvider(PaymentProvider paymentProvider);
}
