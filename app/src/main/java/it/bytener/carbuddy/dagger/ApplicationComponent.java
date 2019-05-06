package it.bytener.carbuddy.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.ui.fragments.MainFragment;

@Singleton
@Component(modules = { ApplicationModule.class, ProviderModule.class })
public interface ApplicationComponent {
    Context getContext();
    AppDatabase getAppDatabase();
    SharedPreferences getSharedPreferences();

    IVehicleProvider getVehicleProvider();
    IInsuranceProvider getInsuranceProvider();
    ICarTaxProvider getCarTaxProvider();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        ApplicationComponent build();
    }
}
