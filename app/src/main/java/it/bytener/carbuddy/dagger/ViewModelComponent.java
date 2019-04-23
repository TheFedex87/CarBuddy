package it.bytener.carbuddy.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.ui.viewmodels.AddOperationViewModelFactory;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

@PerFragment
@Component( dependencies = ApplicationComponent.class, modules = { ProviderModule.class })
public interface ViewModelComponent {
    MainFragmentViewModelFactory getMainFragmentViewModelFactory();
    AddOperationViewModelFactory getAddPaymentViewModelFactory();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder response(IBackgroundOperationResponse response);

        Builder applicationComponent(ApplicationComponent applicationComponent);

        ViewModelComponent build();
    }
}
