package finalproject.database;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class:
 *
 * @author Jarrod Bailey Version 1.0 Course: ITEC 2140 Spring 2017 Section 6
 * Written: , 2017
 */
public class VisitDb {

    private Connection connection;

    public VisitDb() {
        new File("C:\\PatientDatabase\\").mkdir();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\PatientDatabase\\" + PatientContract.DATABASE_NAME);
        } catch (SQLException connectionError) {
            System.out.println("Error connecting to db");
        }

        String createVisitTable = "CREATE TABLE IF NOT EXISTS " + PatientContract.PatientVisitEntry.TABLE_NAME + " ("
                + PatientContract.PatientVisitEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + PatientContract.PatientVisitEntry.COLUMN_P_ID + " INTEGER, "
                + PatientContract.PatientVisitEntry.COLUMN_DOCTOR + " TEXT NOT NULL, "
                + PatientContract.PatientVisitEntry.COLUMN_BP + " TEXT, "
                + PatientContract.PatientVisitEntry.COLUMN_PULSE + " INTEGER, "
                + PatientContract.PatientVisitEntry.COLUMN_TEMP + " INTEGER, "
                + PatientContract.PatientVisitEntry.COLUMN_NOTES + " TEXT, "
                + PatientContract.PatientVisitEntry.COLUMN_DATE + " TEXT, "
                + PatientContract.PatientVisitEntry.COLUMN_REASON + " TEXT, "
                + "FOREIGN KEY (" + PatientContract.PatientVisitEntry.COLUMN_P_ID + ") REFERENCES " + PatientContract.PatientEntry.TABLE_NAME + "(" + PatientContract.PatientEntry.COLUMN_ID + "));";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(createVisitTable);
        } catch (SQLException tableError) {
            System.out.println("error creating visit tables");
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

    public boolean insertVisit(PatientVisit visit) {
        String insert = "INSERT INTO " + PatientContract.PatientVisitEntry.TABLE_NAME + "("
                + PatientContract.PatientVisitEntry.COLUMN_P_ID + ", "
                + PatientContract.PatientVisitEntry.COLUMN_DOCTOR + ", "
                + PatientContract.PatientVisitEntry.COLUMN_BP + ", "
                + PatientContract.PatientVisitEntry.COLUMN_PULSE + ", "
                + PatientContract.PatientVisitEntry.COLUMN_TEMP + ", "
                + PatientContract.PatientVisitEntry.COLUMN_NOTES + ", "
                + PatientContract.PatientVisitEntry.COLUMN_DATE + ", "
                + PatientContract.PatientVisitEntry.COLUMN_REASON + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement insertion = null;
        try {
            insertion = connection.prepareStatement(insert);
            insertion.setInt(1, visit.getPatientId());
            insertion.setString(2, visit.getDoctorName());
            insertion.setString(3, visit.getBloodPressure());
            insertion.setInt(4, visit.getPulse());
            insertion.setInt(5, visit.getTemperature());
            insertion.setString(6, visit.getDoctorNotes());
            insertion.setString(7, visit.getDate());
            insertion.setString(8, visit.getReason());
            insertion.executeUpdate();
            return true;
        } catch (SQLException insertionError) {
            System.out.println("error insertion visit");
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

    public PatientVisit getVisit(int id) { // id of specific visit to load visit scene
        String query = "SELECT * FROM " + PatientContract.PatientVisitEntry.TABLE_NAME + " WHERE " + PatientContract.PatientVisitEntry.COLUMN_ID + " = " + id;
        Statement statement = null;
        ResultSet result = null;
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
                if (statement != null && result != null) {
                    statement.close();
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<PatientVisit> getPatientVisitList(int id) { // id of patient to get patients visits
        String query = "SELECT * FROM " + PatientContract.PatientVisitEntry.TABLE_NAME + " WHERE " + PatientContract.PatientVisitEntry.COLUMN_P_ID + " = " + id;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            LinkedList<PatientVisit> visitList = new LinkedList<PatientVisit>();
            PatientVisit visit = null;
            while (result.next()) {
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
                if (statement != null && result != null) {
                    statement.close();
                    result.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean updateVisit(PatientVisit patientVisit) {
        String update = "UPDATE " + PatientContract.PatientVisitEntry.TABLE_NAME + " SET "
                + PatientContract.PatientVisitEntry.COLUMN_DOCTOR + " = ?, "
                + PatientContract.PatientVisitEntry.COLUMN_BP + " = ?, "
                + PatientContract.PatientVisitEntry.COLUMN_PULSE + " = ?, "
                + PatientContract.PatientVisitEntry.COLUMN_TEMP + " = ?, "
                + PatientContract.PatientVisitEntry.COLUMN_NOTES + " = ?, "
                + PatientContract.PatientVisitEntry.COLUMN_DATE + " = ?, "
                + PatientContract.PatientVisitEntry.COLUMN_REASON + " = ? WHERE "
                + PatientContract.PatientVisitEntry.COLUMN_ID + " = ?;";
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(update);
            updateStatement.setString(1, patientVisit.getDoctorName());
            updateStatement.setString(2, patientVisit.getBloodPressure());
            updateStatement.setInt(3, patientVisit.getPulse());
            updateStatement.setInt(4, patientVisit.getTemperature());
            updateStatement.setString(5, patientVisit.getDoctorNotes());
            updateStatement.setString(6, patientVisit.getDate());
            updateStatement.setString(7, patientVisit.getReason());
            updateStatement.setInt(8, patientVisit.getVisitId());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException updateError) {
            System.out.println("error updating visit");
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

    public boolean deleteVisit(int vId) {
        String deleteVisits = "DELETE FROM " + PatientContract.PatientVisitEntry.TABLE_NAME + " WHERE "
                + PatientContract.PatientVisitEntry.COLUMN_ID + " = ?";
        PreparedStatement statementVisit = null;
        try {
            statementVisit = connection.prepareStatement(deleteVisits);
            statementVisit.setInt(1, vId);
            statementVisit.executeUpdate();
            return true;
        } catch (SQLException deletionError) {
            System.out.println("error deleting");
            System.out.println(deletionError.getMessage());
            return false;
        } finally {
            if (statementVisit != null) {
                try {
                    statementVisit.close();
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

    private PatientVisit constructVisit(ResultSet result) throws SQLException {
        int pId = result.getInt(PatientContract.PatientVisitEntry.COLUMN_P_ID);
        int vId = result.getInt(PatientContract.PatientVisitEntry.COLUMN_ID);
        String doctorName = result.getString(PatientContract.PatientVisitEntry.COLUMN_DOCTOR);
        String bloodPressure = result.getString(PatientContract.PatientVisitEntry.COLUMN_BP);
        int pulse = result.getInt(PatientContract.PatientVisitEntry.COLUMN_PULSE);
        int temp = result.getInt(PatientContract.PatientVisitEntry.COLUMN_TEMP);
        String reason = result.getString(PatientContract.PatientVisitEntry.COLUMN_REASON);
        String doctorNotes = result.getString(PatientContract.PatientVisitEntry.COLUMN_NOTES);
        String date = result.getString(PatientContract.PatientVisitEntry.COLUMN_DATE);
        PatientVisit visit = new PatientVisit(pId, date, doctorName, bloodPressure, pulse, temp, reason, doctorNotes);
        visit.setVisitId(vId);
        return visit;
    }
}
