package it.bytener.carbuddy.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.dagger.AddPaymentFragmentComponent;
import it.bytener.carbuddy.interfaces.IOperationSaver;

public class AddRevisionFragment extends Fragment implements IOperationSaver {
    public AddRevisionFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_revision, container, false);
        return rootView;
    }

    @Override
    public void saveOperation(AddPaymentFragmentComponent addPaymentFragmentComponent, long vehicleId) {

    }
}
