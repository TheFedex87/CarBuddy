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

@Module
public abstract class MockedProviderModule {

    @Binds
    abstract IVehicleProvider bindVehicleProvider(MockVehicleProvider mockVehicleProvider);

    @Binds
    abstract IPaymentProvider bindPaymentProvider(MockPaymentProvider mockPaymentProvider);
}
