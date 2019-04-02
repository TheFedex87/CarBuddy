package it.bytener.carbuddy.interfaces.models;

public interface IVehicle {
    long getId();
    void setId(long id);

    String getBrand();
    void setBrand(String brand);

    String getModel();
    void setModel(String model);

    long getCylinderSize();
    void setCylinderSize(long CylinderSize);

    int getHorsepower();
    void setHorsepower(int horsepower);
}
