package finalproject.database;

import finalproject.database.PatientContract.PatientEntry;
import finalproject.database.PatientContract.PatientVisitEntry;
import java.io.File;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class: PatientDb
 *
 * @author Jarrod Bailey Version 1.0 Course: Advanced Programming Fall 2017
 * Written: , 2017 This class is used to direct interaction with the database
 */
public class PatientDb {

    private Connection connection;

    public PatientDb() {
        new File("C:\\PatientDatabase\\").mkdir();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\PatientDatabase\\" + PatientContract.DATABASE_NAME);
        } catch (SQLException connectionError) {
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
                + PatientEntry.COLUMN_INSURANCE + " TEXT);";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(createPatientTable);
        } catch (SQLException tableError) {
            System.out.println("error creating tables");
            System.out.println(tableError.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean insertPatient(Patient patient) {
        String insert = "INSERT INTO " + PatientEntry.TABLE_NAME + "("
                + PatientEntry.COLUMN_NAME + ", "
                + PatientEntry.COLUMN_ADDRESS + ", "
                + PatientEntry.COLUMN_PHONE + ", "
                + PatientEntry.COLUMN_EMAIL + ", "
                + PatientEntry.COLUMN_DOB + ", "
                + PatientEntry.COLUMN_MARITAL_STATUS + ", "
                + PatientEntry.COLUMN_INSURANCE + ") VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement insertionStatement = null;
        try {
            insertionStatement = connection.prepareStatement(insert);
            insertionStatement.setString(1, patient.getName());
            insertionStatement.setString(2, patient.getAddress());
            insertionStatement.setString(3, patient.getPhoneNumber());
            insertionStatement.setString(4, patient.getEmail());
            insertionStatement.setString(5, patient.getDob());
            insertionStatement.setString(6, patient.getMaritalStatus());
            insertionStatement.setString(7, patient.getInsurance());
            insertionStatement.executeUpdate();
            return true;
        } catch (SQLException insertionError) {
            System.out.println("error inserting");
            System.out.println(insertionError.getMessage());
            return false;
        } finally {
            try {
                if (insertionStatement != null) {
                    insertionStatement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean updatePatient(Patient patient) {
        String update = "UPDATE " + PatientEntry.TABLE_NAME + " SET "
                + PatientEntry.COLUMN_NAME + " = ?, "
                + PatientEntry.COLUMN_ADDRESS + " = ?, "
                + PatientEntry.COLUMN_PHONE + " = ?, "
                + PatientEntry.COLUMN_EMAIL + " = ?, "
                + PatientEntry.COLUMN_DOB + " = ?, "
                + PatientEntry.COLUMN_MARITAL_STATUS + " = ?, "
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
            updateStatement.setString(7, patient.getInsurance());
            updateStatement.setInt(8, patient.getId());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException updateError) {
            System.out.println("error updating");
            System.out.println(updateError.getMessage());
            return false;
        } finally {
            if (updateStatement != null) {
                try {
                    updateStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Patient getPatient(int id) {
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
                if (statement != null && result != null) {
                    statement.close();
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Patient constructPatient(ResultSet result) throws SQLException {
        int id = result.getInt(PatientEntry.COLUMN_ID);
        String name = result.getString(PatientEntry.COLUMN_NAME);
        String address = result.getString(PatientEntry.COLUMN_ADDRESS);
        String phoneNumber = result.getString(PatientEntry.COLUMN_PHONE);
        String email = result.getString(PatientEntry.COLUMN_EMAIL);
        String dob = result.getString(PatientEntry.COLUMN_DOB);
        String maritalStatus = result.getString(PatientEntry.COLUMN_MARITAL_STATUS);
        String insurance = result.getString(PatientEntry.COLUMN_INSURANCE);
        Patient patient = new Patient(name, address, phoneNumber, email, dob, maritalStatus, insurance);
        patient.setId(id);
        return patient;
    }

    public List<Patient> getPatientList() {
        String query = "SELECT * FROM " + PatientEntry.TABLE_NAME + ";";
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            LinkedList<Patient> patientList = new LinkedList<Patient>();
            Patient patient = null;
            while (result.next()) {
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
                if (statement != null && result != null) {
                    statement.close();
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean deletePatient(int id) { // id of patient
        String deleteAllergies = "DELETE FROM " + PatientContract.AllergyEntry.TABLE_NAME + " WHERE "
                + PatientContract.AllergyEntry.COLUMN_P_ID + " = ?";
        String deleteMedications = "DELETE FROM " + PatientContract.MedicationEntry.TABLE_NAME + " WHERE "
                + PatientContract.MedicationEntry.COLUMN_P_ID + " = ?";
        String deleteVisits = "DELETE FROM " + PatientVisitEntry.TABLE_NAME + " WHERE "
                + PatientVisitEntry.COLUMN_P_ID + " = ?";
        String deletePatient = "DELETE FROM " + PatientEntry.TABLE_NAME + " WHERE "
                + PatientEntry.COLUMN_ID + " = ?";
        PreparedStatement statementVisit = null;
        PreparedStatement statementPatient = null;
        PreparedStatement statementMedication = null;
        PreparedStatement statementAllergy = null;
        try {
            statementMedication = connection.prepareStatement(deleteMedications);
            statementMedication.setInt(1, id);
            statementVisit = connection.prepareStatement(deleteVisits);
            statementVisit.setInt(1, id);
            statementPatient = connection.prepareStatement(deletePatient);
            statementPatient.setInt(1, id);
            statementAllergy = connection.prepareStatement(deleteAllergies);
            statementAllergy.setInt(1, id);
            statementAllergy.executeUpdate();
            statementMedication.executeUpdate();
            statementVisit.executeUpdate();
            statementPatient.executeUpdate();
            return true;
        } catch (SQLException deletionError) {
            System.out.println("error deleting");
            System.out.println(deletionError.getMessage());
            return false;
        } finally {
            if (statementVisit != null && statementPatient != null && statementMedication != null && statementAllergy != null) {
                try {
                    statementVisit.close();
                    statementPatient.close();
                    statementMedication.close();
                    statementAllergy.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // add deletion for allergies and medication
    public boolean deleteAllPatients() {
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
        } finally {
            if (statementVisit != null && statementPatient != null) {
                try {
                    statementVisit.close();
                    statementPatient.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean close() {
        try {
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
