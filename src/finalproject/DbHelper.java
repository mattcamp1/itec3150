/*
 */

package finalproject;

import finalproject.database.DatabaseManager;
import finalproject.database.Patient;
import finalproject.database.PatientVisit;
import java.util.List;

/**Class: DbHelper
 * @author Jarrod Bailey
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * This is an abstraction layer for the database, for easier use.
 * Keep in mind database IDs start at 1
 *
 */

public class DbHelper {

    /**
     * Inserts a patient into the database.
     * @param patient The patient to be inserted. A patient id will be assigned automatically by the database.
     * @return boolean true for successful insertion
     */
    public boolean insertPatient(Patient patient){
        DatabaseManager manager = new DatabaseManager();
        boolean success = manager.insertPatient(patient);
        manager.close();
        return success;
    }
    
    /**
     * Inserts a patients visit into database
     * @param visit PatientVisit object
     * @param id Patient id
     * @return boolean true if successful
     */
    public boolean insertVisit(PatientVisit visit){
        DatabaseManager manager = new DatabaseManager();
        boolean success = manager.insertVisit(visit);
        manager.close();
        return success;
    }
    
    /**
     * Updates patient in database
     * @param patient patient to be updated. Do not change id. Must be patient retrieved from database with id already set
     * @return boolean true if successful
     */
    public boolean updatePatient(Patient patient){
        DatabaseManager manager = new DatabaseManager();
        boolean success = manager.updatePatient(patient);
        manager.close();
        return success;
    }
    
    /**
     * Gets patient from database
     * @param id the id of the patient to be retrieved
     * @return patient
     */
    public Patient getPatient(int id){
        DatabaseManager manager = new DatabaseManager();
        Patient patient = manager.getPatient(id);
        manager.close();
        return patient;
    }
    
    /**
     * Gets a list of all the patients in database. For use in list view.
     * @return list of patients
     */
    public List<Patient> getPatientList(){
        DatabaseManager manager = new DatabaseManager();
        List<Patient> list = manager.getPatientList();
        manager.close();
        return list;
    }
    
    /**
     * Gets a specific PatientVisit object
     * @param visitId id of visit to be retrieved
     * @return PatientVisit
     */
    public PatientVisit getVisit(int visitId){
        DatabaseManager manager = new DatabaseManager();
        PatientVisit visit = manager.getVisit(visitId);
        manager.close();
        return visit;
    }
    
    /**
     * Used to get list of visits for a specific patient. Used to populate list view
     * @param patientId id of patient
     * @return List of visits
     */
    public List<PatientVisit> getPatientVisitList(int patientId){
        DatabaseManager manager = new DatabaseManager();
        List<PatientVisit> visitList = manager.getPatientVisitList(patientId);
        manager.close();
        return visitList;
    }
    
     /**
     * Overloaded
     * Used to get list of visits for a specific patient. Used to populate list view
     * @param patient Patient to retrieve visits for
     * @return List of visits
     */
    public List<PatientVisit> getPatientVisitList(Patient patient){
        DatabaseManager manager = new DatabaseManager();
        List<PatientVisit> visitList = manager.getPatientVisitList(patient.getId());
        manager.close();
        return visitList;
    }
    
    /**
     * Deletes patient from database
     * @param patientId id of patient to be deleted
     * @return boolean true for successful deletion
     */
    public boolean deletePatient(int patientId){
        DatabaseManager manager = new DatabaseManager();
        boolean success = manager.deletePatient(patientId);
        manager.close();
        return success;
    }
    
    public boolean clearDatabase(){
       DatabaseManager manager = new DatabaseManager();
        boolean success = manager.deleteAllPatients();
        manager.close();
        return success; 
    }
}
