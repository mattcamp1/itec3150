/*
 */
package finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**Class: FXMLPatientSummaryController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the main JavaFX GUI handling patient summary information
 *
 */
public class FXMLPatientSummaryController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lblTitle;

    @FXML
    private ListView<?> listviewPatients;

    @FXML
    private Button btnNewParient;

    @FXML
    private Button btnRemovePatient;

    @FXML
    private Button btnAddVisit;

    @FXML
    private Label lblPatientId;

    @FXML
    private TextField txtPatientName;

    @FXML
    private TextField txtPatientAddress;

    @FXML
    private TextField txtPatientEmail;

    @FXML
    private ComboBox<?> cboxPatientMaritalStatus;

    @FXML
    private HBox hboxPhone;

    @FXML
    private TextField txtPatientPhoneAreaCode;

    @FXML
    private TextField txtPatientPhonePrefix;

    @FXML
    private TextField txtPatientPhoneLineNumber;

    @FXML
    private HBox hboxAllergies;

    @FXML
    private ListView<?> listviewPatientAllergies;

    @FXML
    private Button btnPatientEditAllergies;

    @FXML
    private HBox hboxMeds;

    @FXML
    private HBox hboxAllergies1;

    @FXML
    private ListView<?> listviewPatientMeds;

    @FXML
    private Button btnPatientEditMeds;

    @FXML
    private TextField txtPatientInsurance;

    @FXML
    private Button btnConfirmPatient;

    @FXML
    void handleAddPatient(ActionEvent event) {

    }

    @FXML
    void handleAddVisit(ActionEvent event) {

    }

    @FXML
    void handleConfirmPatient(ActionEvent event) {

    }

    @FXML
    void handleEditAllergies(ActionEvent event) {
    	Stage allergyDialog = getStage("FXMLEditAllergies.fxml");
    	allergyDialog.initOwner(FinalProject.GetMainInstance());
    	allergyDialog.initModality(Modality.APPLICATION_MODAL);
    	allergyDialog.showAndWait();
	}

	private Stage getStage(String file) {
		Stage dialog = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource(file));
			Scene scene = new Scene(root);
			dialog.setScene(scene);
			return dialog;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

    @FXML
    void handleEditMeds(ActionEvent event) {
    	Stage medicationsDialog = getStage("FXMLEditMeds.fxml");
    	medicationsDialog.initOwner(FinalProject.GetMainInstance());
    	medicationsDialog.initModality(Modality.APPLICATION_MODAL);
    	medicationsDialog.showAndWait();
    }

    @FXML
    void handleRemovePatient(ActionEvent event) {

    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
                
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
