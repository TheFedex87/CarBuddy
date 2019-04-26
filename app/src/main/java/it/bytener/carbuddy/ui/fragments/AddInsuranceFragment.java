package it.bytener.carbuddy.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.bytener.carbuddy.R;
import it.bytener.carbuddy.application.CarBuddyApplication;
import it.bytener.carbuddy.dagger.DaggerApplicationComponent;
import it.bytener.carbuddy.dagger.ViewModelComponent;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.interfaces.IOperationSaver;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.Insurance;
import it.bytener.carbuddy.ui.viewmodels.AddOperationViewModel;
import it.bytener.carbuddy.ui.viewmodels.MainFragmentViewModel;

public class AddInsuranceFragment extends Fragment implements IOperationSaver {
    @BindView(R.id.insurance_cost)
    TextInputEditText cost;
    @BindView(R.id.insurance_payment_date)
    TextInputEditText paymentDate;
    @BindView(R.id.insurance_expiration_date)
    TextInputEditText expirationDate;
    @BindView(R.id.insurance_company_name)
    TextInputEditText companyName;
    @BindView(R.id.insurance_note)
    TextInputEditText note;


    public AddInsuranceFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_insurance, container, false);

        ButterKnife.bind(this, view);

        paymentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                paymentDate.setText(String.valueOf(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                // If you're calling this from a support Fragment
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        expirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                expirationDate.setText(String.valueOf(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                            }
                        },
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
    public void saveOperation(ViewModelComponent viewModelComponent, long vehicleId) {
        //viewModelComponent.inject(this);
        AddOperationViewModel addOperationViewModel = ViewModelProviders.of(this, viewModelComponent.getAddPaymentViewModelFactory()).get(AddOperationViewModel.class);

        Insurance insurance = new Insurance();
        insurance.setInsuranceCompanyName(String.valueOf(companyName.getText()));
        insurance.setNote(String.valueOf(note.getText()));
        long paymentDateMillis = 0;
        try{
            paymentDateMillis = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(paymentDate.getText())).getTime();
        } catch (Exception ex){
            Snackbar.make(getActivity().findViewById(R.id.drawer_layout), getResources().getString(R.string.parse_date_error), Snackbar.LENGTH_LONG).show();
            return;
        }
        long expirationDateMillis = 0;
        try{
            expirationDateMillis = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(expirationDate.getText())).getTime();
        } catch (Exception ex){
            Snackbar.make(getActivity().findViewById(R.id.drawer_layout), getResources().getString(R.string.parse_date_error), Snackbar.LENGTH_LONG).show();
            return;
        }
        insurance.setExpirationDate(expirationDateMillis);
        insurance.setPaymentDate(paymentDateMillis);
        insurance.setVehicleId(vehicleId);

        addOperationViewModel.insertInsurance(insurance);
    }
}
