package it.bytener.carbuddy.interfaces.models;

public interface IReminder {
    String getDescription();
    //void setDescription(String description);

    //Numero di secondi dal 1970 che rappresentano la data di pagamento
    long getPaymentDate();
    void setPaymentDate(long paymentDate);

    //Numero di secondi dal 1970 che rappresentano la data di scadenza
    long getExpirationDate();
    void setExpirationDate(long expirationDate);
}
