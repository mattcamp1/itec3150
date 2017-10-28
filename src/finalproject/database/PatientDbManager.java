/*
 */

package finalproject.database;

import java.util.List;

/**Class: PatientDbManager
 * @author Jarrod Bailey
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * This is an abstraction layer for the database, for easier use.
 * Keep in mind database IDs start at 1
 *
 */

public class PatientDbManager implements DatabaseManager<Patient>{

    /**
     * Inserts a patient into the database.
     * @param patient The patient to be inserted. A patient id will be assigned automatically by the database.
     * @return boolean true for successful insertion
     */
    @Override
    public boolean insert(Patient patient){
        PatientDb manager = new PatientDb();
        boolean success = manager.insertPatient(patient);
        manager.close();
        return success;
    }

    
    /**
     * Updates patient in database
     * @param patient patient to be updated. Do not change id. Must be patient retrieved from database with id already set
     * @return boolean true if successful
     */
    @Override
    public boolean update(Patient patient){
        PatientDb manager = new PatientDb();
        boolean success = manager.updatePatient(patient);
        manager.close();
        return success;
    }
    
    /**
     * Gets patient from database
     * @param id the id of the patient to be retrieved
     * @return patient
     */
    @Override
    public Patient get(int id){
        PatientDb manager = new PatientDb();
        Patient patient = manager.getPatient(id);
        manager.close();
        return patient;
    }
    
    /**
     * Gets a list of all the patients in database. For use in list view.
     * @return list of patients
     */
    @Override
    public List<Patient> getList(int id){
        PatientDb manager = new PatientDb();
        List<Patient> list = manager.getPatientList();
        manager.close();
        return list;
    }
    /**
     * Deletes patient from database
     * @param patientId id of patient to be deleted
     * @return boolean true for successful deletion
     */
    @Override
    public boolean delete(int patientId){
        PatientDb manager = new PatientDb();
        boolean success = manager.deletePatient(patientId);
        manager.close();
        return success;
    }
    
    public boolean clearDatabase(){
       PatientDb manager = new PatientDb();
        boolean success = manager.deleteAllPatients();
        manager.close();
        return success; 
    }
}
