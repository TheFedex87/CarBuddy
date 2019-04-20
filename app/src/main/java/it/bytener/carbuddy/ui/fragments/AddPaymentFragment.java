package it.bytener.carbuddy.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.ui.adapters.OperationPagerAdapter;

public class AddPaymentFragment extends Fragment {
    private Context context;

    private OperationPagerAdapter operationPagerAdapter;

    @BindView(R.id.add_operation_tab)
    TabLayout addOperationTab;
    @BindView(R.id.add_operation_pager)
    ViewPager addOperationPager;

    public AddPaymentFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_operation, container, false);

        ButterKnife.bind(this, rootView);

        operationPagerAdapter = new OperationPagerAdapter(getFragmentManager(), context);
        addOperationPager.setAdapter(operationPagerAdapter);

        addOperationTab.setTabGravity(TabLayout.GRAVITY_FILL);
        addOperationTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        addOperationPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(addOperationTab));
        addOperationTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                addOperationPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }
}
