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
public class Insurance implements IPayment {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long paymentDate;
    private String note;
    private String insuranceCompanyName;
    private long vehicleId;
    private long expirationDate;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }
    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(long vechicleId) {
        this.vehicleId = vechicleId;
    }

    public long getExpirationDate(){ return expirationDate; }
    public void setExpirationDate(long expirationDate) { this.expirationDate = expirationDate; }

    public String getDescription() { return "ASSICURAZIONE"; }
}
