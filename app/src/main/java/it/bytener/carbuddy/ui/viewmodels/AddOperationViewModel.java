package it.bytener.carbuddy.ui.viewmodels;

import androidx.lifecycle.ViewModel;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.room.entities.Insurance;

public class AddOperationViewModel extends ViewModel {
    private IBackgroundOperationResponse response;
    private IInsuranceProvider insuranceProvider;
    private ICarTaxProvider carTaxProvider;

    public AddOperationViewModel(IInsuranceProvider insuranceProvider, ICarTaxProvider carTaxProvider, IBackgroundOperationResponse response){
        this.insuranceProvider = insuranceProvider;
        this.carTaxProvider = carTaxProvider;
        this.response = response;
    }

    public void insertInsurance(Insurance insurance){
        insuranceProvider.insertInsurance(insurance, response);
    }
}
