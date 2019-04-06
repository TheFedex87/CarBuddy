package it.bytener.carbuddy.mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import it.bytener.carbuddy.dagger.MockedProviderModule;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IPaymentProvider;
import it.bytener.carbuddy.room.entities.Payment;

public class MockPaymentProvider implements IPaymentProvider {
    List<Payment> payments;
    List<Payment> paymentsAvailable;
    MutableLiveData<List<Payment>> mutablePayments;
    Random random = new Random();

    public MockPaymentProvider(){
        mutablePayments = new MutableLiveData<>();
        paymentsAvailable = new ArrayList<>();
        payments = new ArrayList<>();

        Payment payment = new Payment();
        payment.setTipology("BOLLO");
        payment.setDescription("Pagamento bollo");
        payment.setVehicleId(0);
        long paymentDateMilli = 0;
        try{
            paymentDateMilli = new SimpleDateFormat("dd-M-yyyy").parse("20-04-2019 00:00:00").getTime();
        } catch(Exception ex){

        }
        payment.setPaymentDate(paymentDateMilli);
        paymentsAvailable.add(payment);
        payments.add(payment);

        payment = new Payment();
        payment.setTipology("ASSICURAZIONE");
        payment.setDescription("Pagamento assicurazione");
        payment.setVehicleId(0);
        paymentDateMilli = 0;
        try{
            paymentDateMilli = new SimpleDateFormat("dd-M-yyyy").parse("02-05-2019 00:00:00").getTime();
        } catch(Exception ex){

        }
        payment.setPaymentDate(paymentDateMilli);
        paymentsAvailable.add(payment);
        payments.add(payment);

        payment = new Payment();
        payment.setTipology("ASSICURAZIONE");
        payment.setDescription("Pagamento assicurazione");
        payment.setVehicleId(1);
        paymentDateMilli = 0;
        try{
            paymentDateMilli = new SimpleDateFormat("dd-M-yyyy").parse("07-06-2019 00:00:00").getTime();
        } catch(Exception ex){

        }
        payment.setPaymentDate(paymentDateMilli);
        paymentsAvailable.add(payment);
        payments.add(payment);

    }

    @Override
    public LiveData<List<Payment>> getPayments(long vehicleId) {
        List<Payment> paymentFiltered = new ArrayList<>();
        for (Payment payment : paymentsAvailable) {
            if(payment.getVehicleId() == vehicleId){
                paymentFiltered.add(payment);
            }
        }

        MutableLiveData<List<Payment>> mutableFiltered = new MutableLiveData<>();
        mutableFiltered.setValue(paymentFiltered);

        return mutableFiltered;
    }

    @Override
    public void insertPayment(Payment payment, IBackgroundOperationResponse response) {
        //if(payment == null){
            int rnd = random.nextInt(paymentsAvailable.size());
            payment = paymentsAvailable.get(rnd);
            response.getResponse(rnd, payment);
        //}
        payments.add(payment);
        mutablePayments.setValue(payments);
    }
}
