package it.bytener.carbuddy.ui.viewmodels;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.interfaces.IVehicleProvider;
import it.bytener.carbuddy.room.entities.Payment;
import it.bytener.carbuddy.room.entities.Vehicle;

public class MainFragmentViewModel extends ViewModel {
    private IVehicleProvider vehicleProvider;
    private IPaymentProvider paymentProvider;
    private IBackgroundOperationResponse response;

    private MutableLiveData<Long> vehicleIdQuery = new MutableLiveData<Long>();
    private LiveData<List<Payment>> paymentOfVehicle;

    public MainFragmentViewModel(IVehicleProvider vehicleProvider, IPaymentProvider paymentProvider, IBackgroundOperationResponse response){
        this.vehicleProvider = vehicleProvider;
        this.paymentProvider = paymentProvider;
        this.response = response;

        vehicleIdQuery.setValue(0l);
        paymentOfVehicle = Transformations.switchMap(vehicleIdQuery, id -> paymentProvider.getPayments(id));
    }

    public LiveData<List<Vehicle>> getVehicles() {
        return vehicleProvider.getVehicles();
    }
    public void setVehicle(Vehicle vehicle){
        vehicleProvider.insertVehicle(vehicle, response);
    }


    public LiveData<List<Payment>> getPayments() { return paymentOfVehicle; }
    public void setPaymentVehicleId(long vehicleId){
        vehicleIdQuery.setValue(vehicleId);
    }
    public void setPayment(Payment payment) { paymentProvider.insertPayment(payment, response);}
}
