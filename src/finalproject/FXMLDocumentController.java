/*
 */
package finalproject;

import finalproject.database.Patient;
import finalproject.database.PatientVisit;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author jnbcb
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
                
        DbHelper helper = new DbHelper();
//        Patient patient = new Patient("bob", "123 street", "444-444-4444", "123@gmail.com", "12/12/2012",
//                "single", "bees", "viagra", "uninsured");
//        Patient patient = helper.getPatient(1);
//        patient.setName("joe");
//        helper.updatePatient(patient);
//        System.out.println(helper.getPatient(1));

//
//        PatientVisit visit = new PatientVisit(patient.getId(), "dr sally",
//               "150/180", 77, 14, "ICD-10", "patient will die", "ibuprofen");
//
 //       helper.insertPatient(patient);
//        helper.insertVisit(visit, 0);
//        List<Patient> list = helper.getPatientList();
//        for (Patient patient1 : list){
//            System.out.println(patient1);
//        }
//        List<PatientVisit> vlist = helper.getPatientVisitList(1);
//        if (vlist.isEmpty()) System.out.println("empty");
//        for (PatientVisit patientV : vlist){
//            System.out.println(patientV);
//        }
        helper.clearDatabase();
        
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
