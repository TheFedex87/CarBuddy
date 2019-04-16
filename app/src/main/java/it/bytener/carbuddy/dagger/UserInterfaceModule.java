package it.bytener.carbuddy.dagger;

import android.content.Context;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.ui.adapters.ReminderAdapter;
import it.bytener.carbuddy.ui.adapters.VehiclePagerAdapter;

@Module
public class UserInterfaceModule {
    private int orientation;
    public UserInterfaceModule(int orientation){
        this.orientation = orientation;
    }
    /*@PerFragment
    @Provides
    public static VehiclePagerAdapter provideVehiclePagerAdapter(Context context, List<IVehicle> vehicleList){
        return new VehiclePagerAdapter(context, vehicleList);
    }*/
    @Provides
    public LinearLayoutManager provideLinearLayoutManager(Context context){
        return new LinearLayoutManager(context, orientation, false);
    }
}
