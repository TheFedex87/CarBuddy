package it.bytener.carbuddy.dagger;

import javax.inject.Singleton;

import dagger.Component;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

@Singleton
@Component( modules = { ViewModelModule.class, ApplicationModule.class })
public interface ViewModelComponent {
    MainFragmentViewModelFactory getMainFragmentViewModelFactory();
}
