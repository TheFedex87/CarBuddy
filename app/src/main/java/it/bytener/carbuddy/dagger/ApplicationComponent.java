package it.bytener.carbuddy.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.ui.fragments.MainFragment;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {
    //void inject(MainFragment mainFragment);

    Context getContext();
    AppDatabase getAppDatabase();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        ApplicationComponent build();
    }
}
