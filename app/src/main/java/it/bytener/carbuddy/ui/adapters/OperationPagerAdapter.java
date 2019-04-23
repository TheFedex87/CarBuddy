package it.bytener.carbuddy.ui.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import it.bytener.carbuddy.ui.fragments.AddCarTaxFragment;
import it.bytener.carbuddy.ui.fragments.AddInsuranceFragment;
import it.bytener.carbuddy.ui.fragments.AddMaintenanceFragment;
import it.bytener.carbuddy.ui.fragments.AddRevisionFragment;
import it.bytener.carbuddy.ui.fragments.AddTireFragment;

public class OperationPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments;
    private Context context;

    public OperationPagerAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        fragments = new Fragment[5];
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment)super.instantiateItem(container, position);
        fragments[position] = fragment;
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
}