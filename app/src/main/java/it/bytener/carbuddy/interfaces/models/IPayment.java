package it.bytener.carbuddy.interfaces.models;

public interface IPayment {
    long getId();
    void setId(long id);

    //Tipologia del pagamento: BOLLO, ASSICURAZIONE, ALTRO..
    String getTipology();
    void setTipology(String tipology);

    String getDescription();
    void setDescription(String description);

    //Numero di secondi dal 1970 che rappresentano la data di pagamento
    long getPaymentDate();
    void setPaymentDate(long paymentDate);

    String getNote();
    void setNote(String note);

    //Chiave esterna che fa riferimento al veicolo
    long getVehicleId();
    void setVehicleId(long vehicleId);
}
