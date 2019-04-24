package it.bytener.carbuddy.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IOperationSaver;

public class AddInsuranceFragment extends Fragment implements DatePickerDialog.OnDateSetListener, IOperationSaver {
    @BindView(R.id.insurance_payment_date)
    TextInputEditText paymentDate;

    public AddInsuranceFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_insurance, container, false);

        ButterKnife.bind(this, view);

        paymentDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            AddInsuranceFragment.this,
                            now.get(Calendar.YEAR), // Initial year selection
                            now.get(Calendar.MONTH), // Initial month selection
                            now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                    );
                    // If you're calling this from a support Fragment
                    dpd.show(getFragmentManager(), "Datepickerdialog");
                }
            }
        });
        paymentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddInsuranceFragment.this,
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                // If you're calling this from a support Fragment
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        return view;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        paymentDate.setText(String.valueOf(dayOfMonth + "/" + monthOfYear + "/" + year));
    }

    @Override
    public void saveOperation(IBackgroundOperationResponse response) {
        Snackbar.make(getActivity().findViewById(R.id.drawer_layout), "Saving insurance", Snackbar.LENGTH_LONG).show();
    }
}
