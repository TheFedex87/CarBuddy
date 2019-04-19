package it.bytener.carbuddy.ui;

import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.dagger.scopes.PerFragment;

@PerFragment
public class NavigationDrawerHeaderViewHolder {
    @BindView(R.id.header_vehicle_brand)
    public TextView headerVehicleBrande;

    @BindView(R.id.header_vehicle_name)
    public TextView headerVehicleName;

    @Inject
    NavigationDrawerHeaderViewHolder(View header){
        ButterKnife.bind(this, header);
    }
}
