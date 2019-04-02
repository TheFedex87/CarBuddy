package it.bytener.carbuddy.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import it.bytener.carbuddy.interfaces.models.IVehicle;

@Entity
public class Vehicle implements IVehicle {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String brand;
    private String model;
    private long cylinderSize;
    private int horsepower;

    @Override
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    @Override
    public String getBrand() {
        return brand;
    }
    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getModel() {
        return this.model;
    }
    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public long getCylinderSize() {
        return cylinderSize;
    }
    @Override
    public void setCylinderSize(long cylinderSyze) {
        this.cylinderSize = cylinderSyze;
    }

    @Override
    public int getHorsepower() {
        return horsepower;
    }
    @Override
    public void setHorsepower(int horsePower) {
        this.horsepower = horsePower;
    }
}
