package finalproject.database;


/**Class: PatientVisit
 * @author Jarrod Bailey
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * This class stores information for each time the patient visits a doctor. Do not use setVisitId outside of databaseManager
 *
 */

public class PatientVisit {

    private int patientId;
    private int visitId;
    private String doctorName;
    private String bloodPressure;
    private int pulse;
    private int temperature;
    private String doctorNotes;
    private String date;
    private String reason;

    public PatientVisit() { }

    public PatientVisit(int patientId, String date, String doctorName, String bloodPressure, int pulse, int temperature, String reason, String doctorNotes) {
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.bloodPressure = bloodPressure;
        this.pulse = pulse;
        this.temperature = temperature;
        this.doctorNotes = doctorNotes;
        this.date = date;
        this.reason = reason;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getVisitId() {
        return visitId;
    }

    protected void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTempereature(int temperature) {
        this.temperature = temperature;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    // change for list view
    @Override
    public String toString(){
        return this.visitId + " " + this.doctorNotes + this.patientId;
    }
    
}
