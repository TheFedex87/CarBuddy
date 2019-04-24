package it.bytener.carbuddy.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.BindsInstance;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.application.CarBuddyApplication;
import it.bytener.carbuddy.dagger.DaggerViewModelComponent;
import it.bytener.carbuddy.dagger.ViewModelComponent;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.room.entities.Insurance;
import it.bytener.carbuddy.room.entities.Vehicle;
import it.bytener.carbuddy.ui.adapters.OperationPagerAdapter;

public class AddPaymentFragment extends Fragment implements IBackgroundOperationResponse {
    private Context context;
    private long vehicleId;

    private ViewModelComponent viewModelComponent;

    private OperationPagerAdapter operationPagerAdapter;

    @BindView(R.id.add_operation_tab)
    TabLayout addOperationTab;
    @BindView(R.id.add_operation_pager)
    ViewPager addOperationPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    public AddPaymentFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;



        viewModelComponent = DaggerViewModelComponent
                .builder()
                .applicationComponent(CarBuddyApplication.appComponent())
                .response(this)
                .build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_operation, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(R.id.action_save):

                operationPagerAdapter.saveCurrentOperation(this, addOperationPager.getCurrentItem());

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_operation, container, false);

        ButterKnife.bind(this, rootView);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        vehicleId = AddPaymentFragmentArgs.fromBundle(getArguments()).getVehicleId();

        operationPagerAdapter = new OperationPagerAdapter(getChildFragmentManager(), context);
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


    @Override
    public void getResponse(long r, Object sender) {

    }
}
