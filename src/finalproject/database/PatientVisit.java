/*
 */

package finalproject.database;

/**Class: PatientVisit
 * @author Jarrod Bailey
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * This class stores information for each time the patient visits a doctor. Do not use setVisitId outside of databaseManager
 *
 */
// change br to temp
public class PatientVisit {

    private int patientId;
    private int visitId;
    private String doctorName;
    private String bloodPressure;
    private int pulse;
    private int breathRate;
    private String icdCode;
    private String doctorNotes;
    private String newPrescriptions;

    // Temporary
    public PatientVisit() { }

    public PatientVisit(int patientId, String doctorName, String bloodPressure, int pulse, int breathRate, String icdCode, String doctorNotes, String newPrescriptions) {
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.bloodPressure = bloodPressure;
        this.pulse = pulse;
        this.breathRate = breathRate;
        this.icdCode = icdCode;
        this.doctorNotes = doctorNotes;
        this.newPrescriptions = newPrescriptions;
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

    public int getBreathRate() {
        return breathRate;
    }

    public void setBreathRate(int breathRate) {
        this.breathRate = breathRate;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public String getNewPrescriptions() {
        return newPrescriptions;
    }

    public void setNewPrescriptions(String newPrescriptions) {
        this.newPrescriptions = newPrescriptions;
    }
    
    @Override
    public String toString(){
        return this.visitId + " " + this.doctorNotes + this.patientId;
    }
    
}
