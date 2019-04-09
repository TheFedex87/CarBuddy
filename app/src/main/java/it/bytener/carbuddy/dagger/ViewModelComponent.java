package it.bytener.carbuddy.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

@Singleton
@Component( modules = { ViewModelModule.class, ApplicationModule.class, ProviderModule.class })
public interface ViewModelComponent {
    MainFragmentViewModelFactory getMainFragmentViewModelFactory();

    @Component.Builder
    interface Builder{
        /*@BindsInstance
        Builder context(Context context);*/

        @BindsInstance
        Builder response(IBackgroundOperationResponse response);

        ViewModelComponent build();
    }
}
