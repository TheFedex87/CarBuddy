package it.bytener.carbuddy.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import it.bytener.carbuddy.dagger.scopes.PerFragment;
import it.bytener.carbuddy.interfaces.IBackgroundOperationResponse;
import it.bytener.carbuddy.interfaces.IInsuranceProvider;
import it.bytener.carbuddy.room.entities.Insurance;

@Singleton
public class MockInsuranceProvider implements IInsuranceProvider {

    private MutableLiveData<List<Insurance>> insurances;

    private List<Insurance> insurancesAvailable;
    private List<Insurance> currentInsuranceList;

    private Random rnd;

    @Inject
    public MockInsuranceProvider(){
        rnd = new Random();
        insurances = new MutableLiveData<>();
        currentInsuranceList = new ArrayList<>();

        insurancesAvailable = new ArrayList<>();
        Insurance insurance = new Insurance();
        insurance.setId(1);
        insurance.setNote("Bla bla bla");
        insurance.setPaymentDate(156151651);
        insurancesAvailable.add(insurance);

        insurance = new Insurance();
        insurance.setId(2);
        insurance.setNote("Bla bla bla");
        insurance.setPaymentDate(14141414);
        insurancesAvailable.add(insurance);

        insurance = new Insurance();
        insurance.setId(3);
        insurance.setNote("Bla bla bla");
        insurance.setPaymentDate(17171717);
        insurancesAvailable.add(insurance);
    }

    @Override
    public LiveData<List<Insurance>> getInsurances(long vehicleId) {
        List<Insurance> filteredInsurances = new ArrayList<>();
        insurances.postValue(filteredInsurances);
        for (Insurance insurance: currentInsuranceList) {
            if(insurance.getVehicleId() == vehicleId)
                filteredInsurances.add(insurance);
        }
        insurances.postValue(filteredInsurances);
        return insurances;
    }

    @Override
    public void insertInsurance(Insurance insurance, IBackgroundOperationResponse response) {
        /*int ind = rnd.nextInt(insurancesAvailable.size());
        Insurance insurance2 = new Insurance();
        insurance2.setVehicleId(insurance.getVehicleId());
        insurance2.setPaymentDate(insurancesAvailable.get(ind).getPaymentDate());
        insurance2.setNote(insurancesAvailable.get(ind).getNote());
        insurance2.setId(insurancesAvailable.get(ind).getId());
        currentInsuranceList.add(insurance2);*/
        currentInsuranceList.add(insurance);
        response.getResponse(currentInsuranceList.size() - 1, insurance);
        //insurances.postValue(currentInsuranceList);
    }
}
