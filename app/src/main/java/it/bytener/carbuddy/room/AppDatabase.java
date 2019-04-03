package it.bytener.carbuddy.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import it.bytener.carbuddy.room.dao.PaymentDao;
import it.bytener.carbuddy.room.dao.VehicleDao;
import it.bytener.carbuddy.room.entities.Payment;
import it.bytener.carbuddy.room.entities.Vehicle;
import timber.log.Timber;

@Database(entities = { Vehicle.class, Payment.class }, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "CARBUDDY_DATABASE";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                Timber.d("Creating new DB instance");
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return instance;
    }

    public abstract VehicleDao vehicleDao();
    public abstract PaymentDao paymentDao();
}
