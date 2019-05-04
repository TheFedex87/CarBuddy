package it.bytener.carbuddy.dagger;

import android.view.View;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.BindsInstance;
import dagger.Component;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.models.IReminder;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.ui.fragments.MainFragment;
import it.bytener.carbuddy.ui.viewmodels.AddOperationViewModelFactory;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModelFactory;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = { UserInterfaceModule.class })
public interface MainFragmentComponent {
    //MainFragmentViewModelFactory getMainFragmentViewModelFactory();

    void inject(MainFragment mainFragment);

    LinearLayoutManager getLinearLayoutManager();

    @Component.Builder
    interface Builder{
        Builder applicationComponent(ApplicationComponent applicationComponent);

        Builder userInterfaceModule(UserInterfaceModule userInterfaceModule);

        @BindsInstance
        Builder vehicleList(List<IVehicle> vehicleList);

        @BindsInstance
        Builder reminderList(List<IReminder> reminderList);

        @BindsInstance
        Builder headerView(View headerView);

        @BindsInstance
        Builder response(IBackgroundOperationResponse response);

        MainFragmentComponent build();
    }
}
