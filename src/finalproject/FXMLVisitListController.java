package finalproject;

import finalproject.database.PatientVisit;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


/**
 * FXML Controller class
 *
 * @author jnbcb
 */
public class FXMLVisitListController extends BaseController<PatientVisit> {

    @FXML
    private ListView<PatientVisit> visitListView;
    @FXML
    private Button viewVisitButton;
    @FXML
    private Button addViewButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // NO-OP
    }    

    @FXML
    private void viewVisit(ActionEvent event) {
    }

    @FXML
    private void addVisit(ActionEvent event) {
    }

    @Override
    public ValidationStatus validateForm() {
        return null;
    }

    @Override
    public void saveToDatabase() {

    }

    @Override
    public void populateData() {
    	// TODO: Fill in data
    }

    @Override
    public void reset() {

    }
}
