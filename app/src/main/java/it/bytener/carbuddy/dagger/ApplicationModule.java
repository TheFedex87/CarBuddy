package it.bytener.carbuddy.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.mock.MockVehicleProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.providers.VehicleProvider;

@Module
public abstract class ApplicationModule {
    //private Context context;

    /*public ApplicationModule(Context context){
        this.context = context;
    }*/

    /*@Singleton
    @Provides
    public Context provideContext(){
        return context;
    }*/

    @Singleton
    @Provides
    public static AppDatabase provideDatabase(Context context){
        return AppDatabase.getInstance(context);
    }

    @Singleton
    @Provides
    public static SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


}
