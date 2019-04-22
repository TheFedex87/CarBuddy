package it.bytener.carbuddy.room.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(indices = { @Index(value = {"vehicleId"})},
        foreignKeys = @ForeignKey(entity = Vehicle.class,
                parentColumns = "id",
                childColumns = "vehicleId",
                onDelete = CASCADE))
public class Insurance implements Payment {
    @PrimaryKey
    private long id;
    private long PaymentDate;
    private String note;
    private String insuranceCompanyName;
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

    public String getDescription() { return "BOLLO"; }
}
