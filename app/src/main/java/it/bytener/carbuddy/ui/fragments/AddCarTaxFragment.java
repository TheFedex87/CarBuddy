package it.bytener.carbuddy.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.dagger.AddPaymentFragmentComponent;
import it.bytener.carbuddy.interfaces.IOperationSaver;

public class AddCarTaxFragment extends Fragment implements IOperationSaver {

    public AddCarTaxFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_car_tax, container, false);
        return view;
    }

    @Override
    public void saveOperation(AddPaymentFragmentComponent addPaymentFragmentComponent, long vehicleId) {
        Snackbar.make(getActivity().findViewById(R.id.drawer_layout), "Saving car tax", Snackbar.LENGTH_LONG).show();
    }
}
