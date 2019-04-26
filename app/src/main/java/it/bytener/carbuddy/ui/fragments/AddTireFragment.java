package it.bytener.carbuddy.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.dagger.ViewModelComponent;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.interfaces.IOperationSaver;

public class AddTireFragment extends Fragment implements IOperationSaver {
    public AddTireFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_tire, container, false);
        return view;
    }

    @Override
    public void saveOperation(ViewModelComponent viewModelComponent, long vehicleId) {

    }
}
