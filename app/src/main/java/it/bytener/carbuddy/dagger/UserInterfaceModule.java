package it.bytener.carbuddy.dagger;

import android.content.Context;

import java.util.List;

import androidx.annotation.Nullable;
import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.ui.adapters.VehiclePagerAdapter;

@Module
public abstract class UserInterfaceModule {

    @PerFragment
    @Provides
    public static VehiclePagerAdapter provideVehiclePagerAdapter(Context context, List<IVehicle> vehicleList){
        return new VehiclePagerAdapter(context, vehicleList);
    }
}
