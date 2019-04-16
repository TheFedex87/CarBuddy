package it.bytener.carbuddy.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.Vehicle;

@PerFragment
public class VehiclePagerAdapter extends PagerAdapter {
    private List<IVehicle> vehicles;
    private Context context;

    @Inject
    public VehiclePagerAdapter(Context context, List<IVehicle> vehicles){
        this.context = context;
        this.vehicles = vehicles;
    }

    @Override
    public int getCount() {
        if(vehicles == null) return 0;
        return vehicles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        LayoutInflater layoutInflater = (LayoutInflater.from(context));
        view = layoutInflater.inflate(R.layout.vehicle_adapter_element, container, false);
        TextView brandTextView = view.findViewById(R.id.vehicle_brand);
        String brand = vehicles.get(position).getBrand();
        brandTextView.setText(brand);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void swapVehicles(List<IVehicle> vehicles){
        this.vehicles = vehicles;
        notifyDataSetChanged();
    }
}
