package finalproject;

import finalproject.database.Medication;
import finalproject.database.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**Class: FXMLEditMedsController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the JavaFX GUI handling medications
 *
 */
public class FXMLEditMedsController extends BaseController<Medication> {


	@FXML
	private TextField txtMedName;

	@FXML
	private Button btnSaveMedication;

	@FXML
	private Button btnResetMedication;

	@FXML
	private TextField txtMedDose;

	@FXML
	private TextField txtMedDoseCount;

	@FXML
	private TextField txtMedReason;
        
        
        private Patient patient;
        private Medication medication;
        private boolean isEdit;

	@FXML
	void handleReset(ActionEvent event) {

	}

	@FXML
	void handleSaveMedication(ActionEvent event) {
            if (isEdit){
                Medication medication = getMedicationFromFields();
                List<Medication> meds = Medication.getMedicationList(patient.getCurrentMedication());
                for (int index = 0; index < meds.size(); index++){
                    if (meds.get(index).getName().equalsIgnoreCase(medication.getName())){
                        meds.set(index, medication);
                        break;
                    }
                }
                String patientMedString = "";
                for (Medication ptMed: meds){
                    patientMedString += ptMed.toString();
                }
                patient.setCurrentMedication(patientMedString);
            } else {
                
            }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO
	}

	@Override
	public void initData(Patient patient,  Medication target) {
		// TODO
                this.patient = patient;
                this.medication = target;
                this.isEdit = true;
                txtMedName.setText(medication.getName());
                txtMedDose.setText(String.valueOf(medication.getDoseMilligrams()));
                txtMedReason.setText(medication.getReason());
                txtMedDoseCount.setText(String.valueOf(medication.getDoseCount()));
	}
        
        private Medication getMedicationFromFields(){
            return new Medication(txtMedName.getText(), txtMedReason.getText(), Integer.valueOf(txtMedDose.getText()), Integer.valueOf(txtMedDoseCount.getText()));
        }
}
