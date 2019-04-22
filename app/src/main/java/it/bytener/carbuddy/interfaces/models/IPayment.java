package it.bytener.carbuddy.interfaces.models;

public interface IPayment extends IReminder {
    long getId();
    void setId(long id);

    //Tipologia del pagamento: BOLLO, ASSICURAZIONE, ALTRO..
    //String getTipology();
    //void setTipology(String tipology);



    String getNote();
    void setNote(String note);

    //Chiave esterna che fa riferimento al veicolo
    long getVehicleId();
    void setVehicleId(long vehicleId);
}
