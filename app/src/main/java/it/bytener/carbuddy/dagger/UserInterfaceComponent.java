package it.bytener.carbuddy.dagger;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.BindsInstance;
import dagger.Component;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.models.IReminder;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.ui.fragments.MainFragment;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = { UserInterfaceModule.class })
public interface UserInterfaceComponent {
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

        UserInterfaceComponent build();
    }
}
