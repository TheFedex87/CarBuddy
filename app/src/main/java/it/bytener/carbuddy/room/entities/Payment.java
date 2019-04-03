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
public class Payment implements IPayment {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String tipology;
    private String description;
    private long paymentDate;
    private String note;
    private long vehicleId;

    @Override
    public long getId() {
        return id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTipology() {
        return tipology;
    }
    @Override
    public void setTipology(String tipology) {
        this.tipology = tipology;
    }

    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public long getPaymentDate() {
        return paymentDate;
    }
    @Override
    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String getNote() {
        return note;
    }
    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public long vehicleId() {
        return vehicleId;
    }
    @Override
    public void vehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
