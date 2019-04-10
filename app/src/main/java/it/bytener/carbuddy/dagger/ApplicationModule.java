package it.bytener.carbuddy.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.mock.MockVehicleProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.providers.VehicleProvider;

@Module
public class ApplicationModule {
    //private Context context;

    /*public ApplicationModule(Context context){
        this.context = context;
    }*/

    /*@Singleton
    @Provides
    public Context provideContext(){
        return context;
    }*/


    @Provides
    public AppDatabase provideDatabase(Context context){
        return AppDatabase.getInstance(context);
    }
}
