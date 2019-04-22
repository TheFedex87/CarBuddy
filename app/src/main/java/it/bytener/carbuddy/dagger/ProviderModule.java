package it.bytener.carbuddy.dagger;


import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.mock.MockVehicleProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.providers.CarTaxProvider;
import it.bytener.carbuddy.room.providers.InsuranceProvider;
import it.bytener.carbuddy.room.providers.VehicleProvider;

@Module
public abstract class ProviderModule {

    @Binds
    abstract IVehicleProvider bindVehicleProvider(VehicleProvider vehicleProvider);

    //@Binds
    //abstract IPaymentProvider bindPaymentProvider(PaymentProvider paymentProvider);

    @Binds
    abstract IInsuranceProvider bindInsuranceProvider(InsuranceProvider insuranceProvider);

    @Binds
    abstract ICarTaxProvider bindCarTaxProvider(CarTaxProvider carTaxProvider);
}
