package finalproject.database;

import java.util.List;


/**
 * Class:
 *
 * @author Jarrod Bailey Version 1.0 Course: ITEC 2140 Spring 2017 Section 6
 * Written: , 2017
 *
 *
 */
public class AllergyDbManager implements DatabaseManager<Allergy> {

    @Override
    public boolean insert(Allergy allergy) {
        AllergyDb manager = new AllergyDb();
        boolean success = manager.insertAllergy(allergy);
        manager.close();
        return success;
    }

    @Override
    public boolean update(Allergy allergy) {
        AllergyDb manager = new AllergyDb();
        boolean success = manager.updateAllergy(allergy);
        manager.close();
        return success;
    }

    @Override
    public Allergy get(int aId) {
        AllergyDb manager = new AllergyDb();
        Allergy allergy = manager.getAllergy(aId);
        manager.close();
        return allergy;
    }

    @Override
    public List<Allergy> getList(int pId) {
        AllergyDb manager = new AllergyDb();
        List<Allergy> list = manager.getAllergyList(pId);
        manager.close();
        return list;
    }

    @Override
    public boolean delete(int aId) {
        AllergyDb manager = new AllergyDb();
        boolean success = manager.deleteAllergy(aId);
        manager.close();
        return success;
    }

}
