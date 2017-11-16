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
public class AllergyDb {

    private Connection connection;

    public AllergyDb() {
        new File("C:\\PatientDatabase\\").mkdir();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\PatientDatabase\\" + PatientContract.DATABASE_NAME);
        } catch (SQLException connectionError) {
            System.out.println("Error connecting to db");
        }

        String createMedicationTable = "CREATE TABLE IF NOT EXISTS " + PatientContract.AllergyEntry.TABLE_NAME + " ("
                + PatientContract.AllergyEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + PatientContract.AllergyEntry.COLUMN_P_ID + " INTEGER NOT NULL, "
                + PatientContract.AllergyEntry.COLUMN_SUBSTANCE + " TEXT NOT NULL, "
                + PatientContract.AllergyEntry.COLUMN_EFFECTS + " TEXT, "
                + PatientContract.AllergyEntry.COLUMN_SEVERITY + " INTEGER, "
                + "FOREIGN KEY (" + PatientContract.AllergyEntry.COLUMN_P_ID
                + ") REFERENCES " + PatientContract.PatientEntry.TABLE_NAME + "(" + PatientContract.PatientEntry.COLUMN_ID + "));";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(createMedicationTable);
        } catch (SQLException tableError) {
            System.out.println("error creating allergies");
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

    public boolean insertAllergy(Allergy allergy) {
        String insert = "INSERT INTO " + PatientContract.AllergyEntry.TABLE_NAME + "("
                + PatientContract.AllergyEntry.COLUMN_P_ID + ", "
                + PatientContract.AllergyEntry.COLUMN_SUBSTANCE + ", "
                + PatientContract.AllergyEntry.COLUMN_EFFECTS + ", "
                + PatientContract.AllergyEntry.COLUMN_SEVERITY
                + ") VALUES (?, ?, ?, ?);";
        PreparedStatement insertion = null;
        try {
            insertion = connection.prepareStatement(insert);
            insertion.setInt(1, allergy.getPatiendId());
            insertion.setString(2, allergy.getSubstance());
            insertion.setString(3, allergy.getEffects());
            insertion.setInt(4, allergy.getSeverity());
            insertion.executeUpdate();
            return true;
        } catch (SQLException insertionError) {
            System.out.println("error insertion allergy");
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

    public Allergy getAllergy(int id) { // id of specific allergy
        String query = "SELECT * FROM " + PatientContract.AllergyEntry.TABLE_NAME + " WHERE " + PatientContract.AllergyEntry.COLUMN_ID + " = " + id;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            result.next();
            return constructAllergy(result);
        } catch (SQLException queryError) {
            System.out.println("error getting allergy");
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

    public List<Allergy> getAllergyList(int patientId) { // id of patient to get patients allergues
        String query = "SELECT * FROM " + PatientContract.AllergyEntry.TABLE_NAME + " WHERE " + PatientContract.AllergyEntry.COLUMN_P_ID + " = " + patientId;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            LinkedList<Allergy> allergyList = new LinkedList<>();
            Allergy allergy = null;
            while (result.next()) {
                allergy = constructAllergy(result);
                allergyList.add(allergy);
            }
            return allergyList;
        } catch (SQLException queryError) {
            System.out.println("error getting allergy list");
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

    public boolean updateAllergy(Allergy allergy) {
        String update = "UPDATE " + PatientContract.AllergyEntry.TABLE_NAME + " SET "
                + PatientContract.AllergyEntry.COLUMN_SUBSTANCE + " = ?, "
                + PatientContract.AllergyEntry.COLUMN_EFFECTS + " = ?, "
                + PatientContract.AllergyEntry.COLUMN_SEVERITY + " = ? WHERE "
                + PatientContract.AllergyEntry.COLUMN_ID + " = ?;";
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(update);
            updateStatement.setString(1, allergy.getSubstance());
            updateStatement.setString(2, allergy.getEffects());
            updateStatement.setInt(3, allergy.getSeverity());
            updateStatement.setInt(4, allergy.getId());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException updateError) {
            System.out.println("error updating allergy");
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

    public boolean deleteAllergy(int aId) { // id of allergy
        String deleteAllergy = "DELETE FROM " + PatientContract.AllergyEntry.TABLE_NAME + " WHERE "
                + PatientContract.AllergyEntry.COLUMN_ID + " = ?";
        PreparedStatement statementAllergy = null;
        try {
            statementAllergy = connection.prepareStatement(deleteAllergy);
            statementAllergy.setInt(1, aId);
            statementAllergy.executeUpdate();
            return true;
        } catch (SQLException deletionError) {
            System.out.println("error deleting allergy");
            System.out.println(deletionError.getMessage());
            return false;
        } finally {
            if (statementAllergy != null) {
                try {
                    statementAllergy.close();
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

    private Allergy constructAllergy(ResultSet result) throws SQLException {
        int pId = result.getInt(PatientContract.AllergyEntry.COLUMN_P_ID);
        int aId = result.getInt(PatientContract.AllergyEntry.COLUMN_ID);
        String substance = result.getString(PatientContract.AllergyEntry.COLUMN_SUBSTANCE);
        String effect = result.getString(PatientContract.AllergyEntry.COLUMN_EFFECTS);
        int severity = result.getInt(PatientContract.AllergyEntry.COLUMN_SEVERITY);
        Allergy allergy = new Allergy(pId, substance, effect, severity);
        allergy.setId(aId);
        return allergy;
    }
}
