/*
 */
package finalproject.database;

import finalproject.database.PatientVisit;
import finalproject.database.Patient;
import finalproject.database.PatientContract.PatientEntry;
import finalproject.database.PatientContract.PatientVisitEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Class: DatabaseManager
 * @author Jarrod Bailey
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * This class is used to direct interaction with the database
 *
 */

public class DatabaseManager {
    
    private Connection connection;
    
    public DatabaseManager(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + PatientContract.DATABASE_NAME);
        } catch (SQLException connectionError){
            System.out.println("Error connecting to db");
        }
        String createPatientTable = "CREATE TABLE IF NOT EXISTS " + PatientEntry.TABLE_NAME + " ("
                + PatientEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + PatientEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + PatientEntry.COLUMN_ADDRESS + " TEXT, "
                + PatientEntry.COLUMN_PHONE + " TEXT, "
                + PatientEntry.COLUMN_EMAIL + " TEXT, "
                + PatientEntry.COLUMN_DOB + " TEXT, "
                + PatientEntry.COLUMN_MARITAL_STATUS + " TEXT, "
                + PatientEntry.COLUMN_ALLERGIES + " TEXT, "
                + PatientEntry.COLUMN_MEDICATION + " TEXT, "
                + PatientEntry.COLUMN_INSURANCE + " TEXT);";
        
        String createVisitTable = "CREATE TABLE IF NOT EXISTS " + PatientVisitEntry.TABLE_NAME + " ("
                + PatientVisitEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + PatientVisitEntry.COLUMN_P_ID + " INTEGER, "
                + PatientVisitEntry.COLUMN_DOCTOR + " TEXT NOT NULL, "
                + PatientVisitEntry.COLUMN_BP + " TEXT, "
                + PatientVisitEntry.COLUMN_PULSE + " INTEGER, "
                + PatientVisitEntry.COLUMN_BREATH + " INTEGER, "
                + PatientVisitEntry.COLUMN_ICD + " TEXT, "
                + PatientVisitEntry.COLUMN_NOTES + " TEXT, "
                + PatientVisitEntry.COLUMN_PRESCRIBED + " TEXT, "
                + "FOREIGN KEY (" + PatientVisitEntry.COLUMN_P_ID + ") REFERENCES " + PatientEntry.TABLE_NAME +"(" + PatientEntry.COLUMN_ID + "));";
        
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(createPatientTable);
            statement.execute(createVisitTable);
        } catch (SQLException tableError){
            System.out.println("error creating tables");
            System.out.println(tableError.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean insertPatient(Patient patient){
        String insert = "INSERT INTO " + PatientEntry.TABLE_NAME + "("
                + PatientEntry.COLUMN_NAME + ", "
                + PatientEntry.COLUMN_ADDRESS + ", "
                + PatientEntry.COLUMN_PHONE + ", "
                + PatientEntry.COLUMN_EMAIL + ", "
                + PatientEntry.COLUMN_DOB + ", "
                + PatientEntry.COLUMN_MARITAL_STATUS + ", "
                + PatientEntry.COLUMN_ALLERGIES + ", "
                + PatientEntry.COLUMN_MEDICATION + ", "
                + PatientEntry.COLUMN_INSURANCE + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement insertionStatement = null;
        try {
            insertionStatement = connection.prepareStatement(insert);
            insertionStatement.setString(1, patient.getName());
            insertionStatement.setString(2, patient.getAddress());
            insertionStatement.setString(3, patient.getPhoneNumber());
            insertionStatement.setString(4, patient.getEmail());
            insertionStatement.setString(5, patient.getDob());
            insertionStatement.setString(6, patient.getMaritalStatus());
            insertionStatement.setString(7, patient.getAllergies());
            insertionStatement.setString(8, patient.getCurrentMedication());
            insertionStatement.setString(9, patient.getInsurance());
            insertionStatement.executeUpdate();
            return true;
        } catch (SQLException insertionError) {
            System.out.println("error inserting");
            System.out.println(insertionError.getMessage());
            return false;
        } finally {
            try {
                if (insertionStatement != null) insertionStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean insertVisit(PatientVisit visit){
        String insert = "INSERT INTO " + PatientVisitEntry.TABLE_NAME + "("
                + PatientVisitEntry.COLUMN_P_ID + ", "
                + PatientVisitEntry.COLUMN_DOCTOR + ", "
                + PatientVisitEntry.COLUMN_BP + ", "
                + PatientVisitEntry.COLUMN_PULSE + ", "
                + PatientVisitEntry.COLUMN_BREATH + ", "
                + PatientVisitEntry.COLUMN_ICD + ", "
                + PatientVisitEntry.COLUMN_NOTES + ", "
                + PatientVisitEntry.COLUMN_PRESCRIBED + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement insertion = null;
        try {
            insertion = connection.prepareStatement(insert);
            insertion.setInt(1, visit.getPatientId());
            insertion.setString(2, visit.getDoctorName());
            insertion.setString(3, visit.getBloodPressure());
            insertion.setInt(4, visit.getPulse());
            insertion.setInt(5, visit.getBreathRate());
            insertion.setString(6, visit.getIcdCode());
            insertion.setString(7, visit.getDoctorNotes());
            insertion.setString(8, visit.getNewPrescriptions());
            insertion.executeUpdate();
            return true;
        } catch (SQLException insertionError) {
            System.out.println("error insertion visit");
            System.out.println(insertionError.getMessage());
            return false;
        } finally {
            try {
                 if (insertion != null) insertion.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean updatePatient(Patient patient){
        String update  = "UPDATE " + PatientEntry.TABLE_NAME + " SET "
                + PatientEntry.COLUMN_NAME + " = ?, "
                + PatientEntry.COLUMN_ADDRESS + " = ?, "
                + PatientEntry.COLUMN_PHONE + " = ?, "
                + PatientEntry.COLUMN_EMAIL + " = ?, "
                + PatientEntry.COLUMN_DOB + " = ?, "
                + PatientEntry.COLUMN_MARITAL_STATUS + " = ?, "
                + PatientEntry.COLUMN_ALLERGIES + " = ?, "
                + PatientEntry.COLUMN_MEDICATION + " = ?, "
                + PatientEntry.COLUMN_INSURANCE + " = ? WHERE "
                + PatientEntry.COLUMN_ID + " = ?;";
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(update);
            updateStatement.setString(1, patient.getName());
            updateStatement.setString(2, patient.getAddress());
            updateStatement.setString(3, patient.getPhoneNumber());
            updateStatement.setString(4, patient.getEmail());
            updateStatement.setString(5, patient.getDob());
            updateStatement.setString(6, patient.getMaritalStatus());
            updateStatement.setString(7, patient.getAllergies());
            updateStatement.setString(8, patient.getCurrentMedication());
            updateStatement.setString(9, patient.getInsurance());
            updateStatement.setInt(10, patient.getId());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException updateError) {
            System.out.println("error updating");
            System.out.println(updateError.getMessage());
            return false;
        } finally {
            if (updateStatement != null) try {
                updateStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Patient getPatient(int id){
        String query = "SELECT * FROM " + PatientEntry.TABLE_NAME + " WHERE " + PatientEntry.COLUMN_ID + " = " + id;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            result.next();
            Patient patient = constructPatient(result);
            return patient;
        } catch (SQLException queryError) {
            System.out.println("error getting patient");
            System.out.println(queryError.getMessage());
            return null;
        } finally {
            try {
                if (statement != null && result != null){
                    statement.close();
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Patient constructPatient(ResultSet result) throws SQLException{
            int id = result.getInt(PatientEntry.COLUMN_ID);
            String name = result.getString(PatientEntry.COLUMN_NAME);
            String address = result.getString(PatientEntry.COLUMN_ADDRESS);
            String phoneNumber = result.getString(PatientEntry.COLUMN_PHONE);
            String email = result.getString(PatientEntry.COLUMN_EMAIL);
            String dob = result.getString(PatientEntry.COLUMN_DOB);
            String maritalStatus = result.getString(PatientEntry.COLUMN_MARITAL_STATUS);
            String allergies = result.getString(PatientEntry.COLUMN_ALLERGIES);
            String currentMedication = result.getString(PatientEntry.COLUMN_MEDICATION);
            String insurance = result.getString(PatientEntry.COLUMN_INSURANCE);
            Patient patient = new Patient(name, address, phoneNumber, email, dob, maritalStatus, allergies,
            currentMedication, insurance);
            patient.setId(id);
            return patient;
    }
    
    public List<Patient> getPatientList(){
        String query = "SELECT * FROM " + PatientEntry.TABLE_NAME + ";";
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            LinkedList<Patient> patientList = new LinkedList<>();
            Patient patient = null;
            while (result.next()){
                patient = constructPatient(result);
                patientList.add(patient);
            }
            return patientList;   
        } catch (SQLException queryError) {
            System.out.println("error getting patient list");
            System.out.println(queryError.getMessage());
            return null;
        } finally {
            try {
                if (statement != null && result != null){
                    statement.close();
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private PatientVisit constructVisit(ResultSet result) throws SQLException{
        int pId = result.getInt(PatientVisitEntry.COLUMN_P_ID);
        int vId = result.getInt(PatientVisitEntry.COLUMN_ID);
        String doctorName = result.getString(PatientVisitEntry.COLUMN_DOCTOR);
        String bloodPressure = result.getString(PatientVisitEntry.COLUMN_BP);
        int pulse = result.getInt(PatientVisitEntry.COLUMN_PULSE);
        int breathRate = result.getInt(PatientVisitEntry.COLUMN_BREATH);
        String icdCode = result.getString(PatientVisitEntry.COLUMN_ICD);
        String doctorNotes = result.getString(PatientVisitEntry.COLUMN_NOTES);
        String newPrescription = result.getString(PatientVisitEntry.COLUMN_PRESCRIBED);
        PatientVisit visit = new PatientVisit(pId, doctorName, bloodPressure, pulse, breathRate, icdCode, doctorNotes, newPrescription);
        visit.setVisitId(vId);
        return visit;
    }
    
    public PatientVisit getVisit(int id){ // id of specific visit to load visit scene
        String query = "SELECT * FROM " + PatientVisitEntry.TABLE_NAME + " WHERE " + PatientVisitEntry.COLUMN_ID + " = " + id;
        Statement statement = null;
        ResultSet result =  null;
          try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            result.next();
            return constructVisit(result);
        } catch (SQLException queryError) {
            System.out.println("error getting visit");
            System.out.println(queryError.getMessage());
            return null;
        } finally {
              try {
                if (statement != null && result != null){
                    statement.close();
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
    }
    
    public List<PatientVisit> getPatientVisitList(int id){ // id of patient to get patients visits
        String query = "SELECT * FROM " + PatientVisitEntry.TABLE_NAME + " WHERE " + PatientVisitEntry.COLUMN_P_ID + " = " + id;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            LinkedList<PatientVisit> visitList = new LinkedList<>();
            PatientVisit visit = null;
            while(result.next()){
                visit = constructVisit(result);
                visitList.add(visit);
            }
            return visitList;
        } catch (SQLException queryError) {
            System.out.println("error getting visit list");
            System.out.println(queryError.getMessage());
            return null;
        } finally {
              try {
                if (statement != null && result != null){
                    statement.close();
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
    }
    
    public boolean deletePatient(int id){ // id of patient
        String deleteVisits = "DELETE FROM " + PatientVisitEntry.TABLE_NAME + " WHERE "
                + PatientVisitEntry.COLUMN_P_ID + " = ?";
        String deletePatient = "DELETE FROM " + PatientEntry.TABLE_NAME + " WHERE " 
                + PatientEntry.COLUMN_ID + " = ?";
        PreparedStatement statementVisit = null;
        PreparedStatement statementPatient = null;
        try {
            statementVisit = connection.prepareStatement(deleteVisits);
            statementVisit.setInt(1, id);
            statementPatient = connection.prepareStatement(deletePatient);
            statementPatient.setInt(1, id);
            statementVisit.executeUpdate();
            statementPatient.executeUpdate();
            return true;
        } catch (SQLException deletionError) {
            System.out.println("error deleting");
            System.out.println(deletionError.getMessage());
            return false;
        } finally{
            if (statementVisit != null && statementPatient != null){
                try {
                    statementVisit.close();
                    statementPatient.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean deleteAllPatients(){
        String deleteVisits = "DELETE FROM " + PatientVisitEntry.TABLE_NAME + ";";
        String deletePatient = "DELETE FROM " + PatientEntry.TABLE_NAME + ";";
        PreparedStatement statementVisit = null;
        PreparedStatement statementPatient = null;
        try {
            statementVisit = connection.prepareStatement(deleteVisits);
            statementPatient = connection.prepareStatement(deletePatient);
            statementVisit.executeUpdate();
            statementPatient.executeUpdate();
            return true;
        } catch (SQLException deletionError) {
            System.out.println("error deleting database");
            System.out.println(deletionError.getMessage());
            return false;
        } finally{
            if (statementVisit != null && statementPatient != null){
                try {
                    statementVisit.close();
                    statementPatient.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean close(){
        try {
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // add some checks to classes
        
       
}
