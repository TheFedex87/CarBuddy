package it.bytener.carbuddy.dagger;

import androidx.fragment.app.FragmentManager;
import dagger.BindsInstance;
import dagger.Component;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.ui.fragments.AddInsuranceFragment;
import it.bytener.carbuddy.ui.fragments.AddPaymentFragment;
import it.bytener.carbuddy.ui.viewmodels.AddOperationViewModelFactory;

@PerFragment
@Component( dependencies = ApplicationComponent.class)
public interface AddPaymentFragmentComponent {
    AddOperationViewModelFactory getAddPaymentViewModelFactory();

    void inject(AddPaymentFragment addPaymentFragment);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder response(IBackgroundOperationResponse response);

        @BindsInstance
        Builder fragmentManager(FragmentManager fragmentManager);

        Builder applicationComponent(ApplicationComponent applicationComponent);

        AddPaymentFragmentComponent build();
    }
}
