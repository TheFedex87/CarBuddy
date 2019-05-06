package it.bytener.carbuddy.ui.adapters;

import android.content.Context;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import it.bytener.carbuddy.dagger.AddPaymentFragmentComponent;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IOperationSaver;
import it.bytener.carbuddy.ui.fragments.AddCarTaxFragment;
import it.bytener.carbuddy.ui.fragments.AddInsuranceFragment;
import it.bytener.carbuddy.ui.fragments.AddMaintenanceFragment;
import it.bytener.carbuddy.ui.fragments.AddRevisionFragment;
import it.bytener.carbuddy.ui.fragments.AddTireFragment;

@PerFragment
public class OperationPagerAdapter extends FragmentPagerAdapter {

    private IOperationSaver[] fragments;
    private Context context;

    @Inject
    public OperationPagerAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        fragments = new IOperationSaver[5];
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment)super.instantiateItem(container, position);
        fragments[position] = (IOperationSaver)fragment;
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = Fragment.instantiate(context, AddInsuranceFragment.class.getName());
                break;
            case 1:
                fragment = Fragment.instantiate(context, AddCarTaxFragment.class.getName());
                break;
            case 2:
                fragment = Fragment.instantiate(context, AddMaintenanceFragment.class.getName());
                break;
            case 3:
                fragment = Fragment.instantiate(context, AddTireFragment.class.getName());
                break;
            case 4:
                fragment = Fragment.instantiate(context, AddRevisionFragment.class.getName());
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    public void saveCurrentOperation(int position, long vehicleId, AddPaymentFragmentComponent addPaymentFragmentComponent){
        fragments[position].saveOperation(addPaymentFragmentComponent, vehicleId);
    }
}
