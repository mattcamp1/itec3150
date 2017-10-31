package finalproject.database;

import java.util.List;
import java.util.Observable;


/**
 * Class:
 *
 * @author Jarrod Bailey Version 1.0 Course: ITEC 2140 Spring 2017 Section 6
 * Written: , 2017
 *
 *
 */
public class MedicationDbManager  extends Observable implements DatabaseManager<Medication> {

    @Override
    public boolean insert(Medication medication) {
        MedicationDb manager = new MedicationDb();
        boolean success = manager.insertMedication(medication);
        manager.close();
        setChanged();
        notifyObservers();
        return success;
    }

    @Override
    public boolean update(Medication medication) {
        MedicationDb manager = new MedicationDb();
        boolean success = manager.updateMedication(medication);
        manager.close();
        setChanged();
        notifyObservers();
        return success;
    }

    @Override
    public Medication get(int mId) {
        MedicationDb manager = new MedicationDb();
        Medication medication = manager.getMedication(mId);
        manager.close();
        return medication;
    }

    @Override
    public List<Medication> getList(int pId) {
        MedicationDb manager = new MedicationDb();
        List<Medication> list = manager.getMedicationList(pId);
        manager.close();
        return list;
    }

    @Override
    public boolean delete(int mId) {
        MedicationDb manager = new MedicationDb();
        boolean success = manager.deleteMedication(mId);
        manager.close();
        setChanged();
        notifyObservers();
        return success;
    }

}
