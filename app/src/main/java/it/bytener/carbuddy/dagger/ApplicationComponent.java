package it.bytener.carbuddy.dagger;

import javax.inject.Singleton;

import dagger.Component;
import it.bytener.carbuddy.ui.fragments.MainFragment;

@Singleton
@Component(modules =  { ApplicationModule.class } )
public interface ApplicationComponent {
    void inject(MainFragment mainFragment);
}
