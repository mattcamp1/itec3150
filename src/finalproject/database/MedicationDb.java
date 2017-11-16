/*
 */
package finalproject.database;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 * Class:
 *
 * @author Jarrod Bailey Version 1.0 Course: ITEC 2140 Spring 2017 Section 6
 * Written: , 2017
 */
public class MedicationDb {

    private Connection connection;

    public MedicationDb() {
        new File("C:\\PatientDatabase\\").mkdir();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\PatientDatabase\\" + PatientContract.DATABASE_NAME);
        } catch (SQLException connectionError) {
            System.out.println("Error connecting to db");
        }

        String createMedicationTable = "CREATE TABLE IF NOT EXISTS " + PatientContract.MedicationEntry.TABLE_NAME + " ("
                + PatientContract.MedicationEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + PatientContract.MedicationEntry.COLUMN_P_ID + " INTEGER NOT NULL, "
                + PatientContract.MedicationEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + PatientContract.MedicationEntry.COLUMN_REASON + " TEXT, "
                + PatientContract.MedicationEntry.COLUMN_DOSE_MIL + " INTEGER NOT NULL, "
                + PatientContract.MedicationEntry.COLUMN_COUNT + " INTEGER NOT NULL, "
                + "FOREIGN KEY (" + PatientContract.MedicationEntry.COLUMN_P_ID
                + ") REFERENCES " + PatientContract.PatientEntry.TABLE_NAME + "(" + PatientContract.PatientEntry.COLUMN_ID + "));";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(createMedicationTable);
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

    public boolean insertMedication(Medication medication) {
        String insert = "INSERT INTO " + PatientContract.MedicationEntry.TABLE_NAME + "("
                + PatientContract.MedicationEntry.COLUMN_P_ID + ", "
                + PatientContract.MedicationEntry.COLUMN_NAME + ", "
                + PatientContract.MedicationEntry.COLUMN_REASON + ", "
                + PatientContract.MedicationEntry.COLUMN_DOSE_MIL + ", "
                + PatientContract.MedicationEntry.COLUMN_COUNT
                + ") VALUES (?, ?, ?, ?, ?);";
        PreparedStatement insertion = null;
        try {
            insertion = connection.prepareStatement(insert);
            insertion.setInt(1, medication.getPatiendId());
            insertion.setString(2, medication.getName());
            insertion.setString(3, medication.getReason());
            insertion.setInt(4, medication.getDoseMilligrams());
            insertion.setInt(5, medication.getDoseCount());
            insertion.executeUpdate();
            return true;
        } catch (SQLException insertionError) {
            System.out.println("error insertion medication");
            System.out.println(insertionError.getMessage());
            return false;
        } finally {
            try {
                if (insertion != null) {
                    insertion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Medication getMedication(int id) { // id of specific medication
        String query = "SELECT * FROM " + PatientContract.MedicationEntry.TABLE_NAME + " WHERE " + PatientContract.MedicationEntry.COLUMN_ID + " = " + id;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            result.next();
            return constructMedication(result);
        } catch (SQLException queryError) {
            System.out.println("error getting medication");
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

    public List<Medication> getMedicationList(int patientId) { // id of patient to get patients meds
        String query = "SELECT * FROM " + PatientContract.MedicationEntry.TABLE_NAME + " WHERE " + PatientContract.MedicationEntry.COLUMN_P_ID + " = " + patientId;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            LinkedList<Medication> medicationList = new LinkedList<>();
            Medication medication = null;
            while (result.next()) {
                medication = constructMedication(result);
                medicationList.add(medication);
            }
            return medicationList;
        } catch (SQLException queryError) {
            System.out.println("error getting medication list");
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

    public boolean updateMedication(Medication medication) {
        String update = "UPDATE " + PatientContract.MedicationEntry.TABLE_NAME + " SET "
                + PatientContract.MedicationEntry.COLUMN_NAME + " = ?, "
                + PatientContract.MedicationEntry.COLUMN_REASON + " = ?, "
                + PatientContract.MedicationEntry.COLUMN_DOSE_MIL + " = ?, "
                + PatientContract.MedicationEntry.COLUMN_COUNT + " = ? WHERE "
                + PatientContract.MedicationEntry.COLUMN_ID + " = ?;";
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(update);
            updateStatement.setString(1, medication.getName());
            updateStatement.setString(2, medication.getReason());
            updateStatement.setInt(3, medication.getDoseMilligrams());
            updateStatement.setInt(4, medication.getDoseCount());
            updateStatement.setInt(5, medication.getId());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException updateError) {
            System.out.println("error updating medication");
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

    public boolean deleteMedication(int mId) { // id of medication
        String deleteMedication = "DELETE FROM " + PatientContract.MedicationEntry.TABLE_NAME + " WHERE "
                + PatientContract.MedicationEntry.COLUMN_ID + " = ?";
        PreparedStatement statementMedication = null;
        try {
            statementMedication = connection.prepareStatement(deleteMedication);
            statementMedication.setInt(1, mId);
            statementMedication.executeUpdate();
            return true;
        } catch (SQLException deletionError) {
            System.out.println("error deleting medication");
            System.out.println(deletionError.getMessage());
            return false;
        } finally {
            if (statementMedication != null) {
                try {
                    statementMedication.close();
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

    private Medication constructMedication(ResultSet result) throws SQLException {
        int pId = result.getInt(PatientContract.MedicationEntry.COLUMN_P_ID);
        int mId = result.getInt(PatientContract.MedicationEntry.COLUMN_ID);
        String name = result.getString(PatientContract.MedicationEntry.COLUMN_NAME);
        String reason = result.getString(PatientContract.MedicationEntry.COLUMN_REASON);
        int doseMil = result.getInt(PatientContract.MedicationEntry.COLUMN_DOSE_MIL);
        int doseCount = result.getInt(PatientContract.MedicationEntry.COLUMN_COUNT);
        Medication medication = new Medication(pId, name, reason, doseMil, doseCount);
        medication.setId(mId);
        return medication;
    }
}
