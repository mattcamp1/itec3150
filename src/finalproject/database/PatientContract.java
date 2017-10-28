/*
 */

package finalproject.database;

/**Class: Patient Contract
 * @author Jarrod Bailey
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * This class provides constants for use with the corresponding database. This helps prevent errors in the database.
 *
 */

public class PatientContract {
    
    public static final String DATABASE_NAME = "Patients.db";
    
    public static final class PatientEntry{
        
        public static final String TABLE_NAME = "patients";
        
        public static final String COLUMN_ID = "p_id";
        
        public static final String COLUMN_NAME = "name";
                
        public static final String COLUMN_ADDRESS = "address";
        
        public static final String COLUMN_PHONE = "phone_number";
        
        public static final String COLUMN_EMAIL = "email";
        
        public static final String COLUMN_DOB = "dob";
        
        public static final String COLUMN_MARITAL_STATUS = "marital_status";
        
        public static final String COLUMN_ALLERGIES = "allergies";
        
        public static final String COLUMN_MEDICATION = "current_medication";
        
        public static final String COLUMN_INSURANCE = "insurance";
        
    }
    
    public static final class PatientVisitEntry{
        
        public static final String TABLE_NAME = "patient_visits";
        
        public static final String COLUMN_P_ID = "p_id";
        
        public static final String COLUMN_ID = "v_id";
        
        public static final String COLUMN_DOCTOR = "doctor_name";
        
        public static final String COLUMN_BP = "blood_pressure";
        
        public static final String COLUMN_PULSE = "pulse";
        
        public static final String COLUMN_TEMP = "temperature";
        
        public static final String COLUMN_NOTES = "doctor_notes";
        
        public static final String COLUMN_DATE = "date";
        
        public static final String COLUMN_REASON = "reason";
        
    }
    
    public static final class MedicationEntry{
        
        public static final String TABLE_NAME = "medications";
                
        public static final String COLUMN_P_ID = "p_id";
                
        public static final String COLUMN_ID = "m_id";
               
        public static final String COLUMN_NAME = "name";
        
        public static final String COLUMN_REASON = "reason";
        
        public static final String COLUMN_DOSE_MIL = "miligrams";
        
        public static final String COLUMN_COUNT = "dose_count";
    }
    
    public static final class AllergyEntry{
        
        public static final String TABLE_NAME = "allergies";
        
         public static final String COLUMN_P_ID = "p_id";
         
         public static final String COLUMN_ID = "a_id";
         
         public static final String COLUMN_SUBSTANCE = "substance";
         
         public static final String COLUMN_EFFECTS = "effects";
         
         public static final String COLUMN_SEVERITY = "severity";
    }
}
