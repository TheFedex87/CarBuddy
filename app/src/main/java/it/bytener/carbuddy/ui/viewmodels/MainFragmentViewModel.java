package it.bytener.carbuddy.ui.viewmodels;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.ICarTaxProvider;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.interfaces.models.IVehicle;
import it.bytener.carbuddy.room.entities.CarTax;
import it.bytener.carbuddy.room.entities.Insurance;
import it.bytener.carbuddy.room.entities.Payment;
import it.bytener.carbuddy.room.entities.Vehicle;

public class MainFragmentViewModel extends ViewModel {
    private IVehicleProvider vehicleProvider;
    private IInsuranceProvider insuranceProvider;
    private ICarTaxProvider carTaxProvider;
    private IBackgroundOperationResponse response;

    private MutableLiveData<Long> vehicleIdQuery = new MutableLiveData<Long>();
    private LiveData<List<Insurance>> insuranceOfVehicle;
    private LiveData<List<CarTax>> carTaxOfVehicle;

    private MutableLiveData<IVehicle> selectedVehicle = new MutableLiveData<>();;

    public MainFragmentViewModel(){

    }

    public MainFragmentViewModel(IVehicleProvider vehicleProvider, IInsuranceProvider insuranceProvider, ICarTaxProvider carTaxProvider, IBackgroundOperationResponse response){
        this.vehicleProvider = vehicleProvider;
        this.insuranceProvider = insuranceProvider;
        this.carTaxProvider = carTaxProvider;
        this.response = response;

        insuranceOfVehicle = Transformations.switchMap(vehicleIdQuery, id -> insuranceProvider.getInsurances(id));
        carTaxOfVehicle = Transformations.switchMap(vehicleIdQuery, id -> carTaxProvider.getCarTaxes(id));
    }

    public LiveData<List<Vehicle>> getVehicles() {
        return vehicleProvider.getVehicles();
    }
    public void setVehicle(Vehicle vehicle){
        vehicleProvider.insertVehicle(vehicle, response);
    }

    public void setVehicleId(long vehicleId){
        vehicleIdQuery.setValue(vehicleId);
    }

    public LiveData<List<Insurance>> getInsurances() { return insuranceOfVehicle; }
    public void setInsurance(Insurance insurance) { insuranceProvider.insertInsurance(insurance, response);}

    public LiveData<List<CarTax>> getCarTaxes() { return carTaxOfVehicle; }
    public void setCarTax(CarTax carTax) { carTaxProvider.insertCarTax(carTax, response);}

    public void setSelectedVehicle(IVehicle vehicle){
        selectedVehicle.setValue(vehicle);
    }
    public LiveData<IVehicle> getSelectedVehicle(){
        return selectedVehicle;
    }
}
