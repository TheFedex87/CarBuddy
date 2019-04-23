package it.bytener.carbuddy.ui.viewmodels;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.room.AppDatabase;
import it.bytener.carbuddy.room.entities.Insurance;
import it.bytener.carbuddy.room.providers.InsuranceProvider;

@PerFragment
public class AddOperationViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private IBackgroundOperationResponse response;
    private IInsuranceProvider insuranceProvider;
    private ICarTaxProvider carTaxProvider;

    @Inject
    public AddOperationViewModelFactory(IInsuranceProvider insuranceProvider, ICarTaxProvider carTaxProvider, IBackgroundOperationResponse response){
        this.insuranceProvider = insuranceProvider;
        this.carTaxProvider = carTaxProvider;
        this.response = response;
    }

    public <T extends ViewModel>T create(Class<T> modelClass){
        return (T) new AddOperationViewModel(insuranceProvider, carTaxProvider, response);
    }
}
