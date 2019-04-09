package it.bytener.carbuddy.application;

import android.app.Application;

import it.bytener.carbuddy.dagger.ApplicationComponent;
import it.bytener.carbuddy.dagger.ApplicationModule;
import it.bytener.carbuddy.dagger.DaggerApplicationComponent;

public class CarBuddyApplication extends Application {
    private static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //appComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(getApplicationContext())).build();
        appComponent = DaggerApplicationComponent.builder().context(getApplicationContext()).build();
    }

    public static ApplicationComponent appComponent(){
        return appComponent;
    }
}
