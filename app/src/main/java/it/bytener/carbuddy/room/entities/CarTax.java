package it.bytener.carbuddy.room.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import it.bytener.carbuddy.interfaces.models.IPayment;

import static androidx.room.ForeignKey.CASCADE;

@Entity(indices = { @Index(value = {"vehicleId"})},
        foreignKeys = @ForeignKey(entity = Vehicle.class,
                parentColumns = "id",
                childColumns = "vehicleId",
                onDelete = CASCADE))
public class CarTax implements IPayment {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long PaymentDate;
    private String note;
    private int sanction;
    private long vehicleId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getPaymentDate() {
        return PaymentDate;
    }
    public void setPaymentDate(long paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public int getSanction() {
        return sanction;
    }
    public void setSanction(int sanction) {
        this.sanction = sanction;
    }

    public long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String getDescription(){ return "Bollo"; }
}
