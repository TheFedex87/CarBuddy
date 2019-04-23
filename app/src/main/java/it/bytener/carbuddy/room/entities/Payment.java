package it.bytener.carbuddy.room.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import it.bytener.carbuddy.interfaces.models.IPayment;

import static androidx.room.ForeignKey.CASCADE;

//@DatabaseView("SELECT insurance.paymentDate, insurance.note, carTax.paymentDate, carTax.note WHERE insurance.vehicleId = {vehicleId} OR carTax.vehicleId = {vehicleId}")
public interface Payment extends IPayment {

    /*long getId();
    void setId(long id);

    long getPaymentDate();
    void setPaymentDate(long paymentDate);

    String getNote();
    void setNote(String note);

    long getVehicleId();
    void setVehicleId(long vehicleId);*/
}
