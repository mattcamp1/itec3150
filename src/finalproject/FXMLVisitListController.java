package finalproject;

import finalproject.database.*;
import finalproject.helpers.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author jnbcb
 */
public class FXMLVisitListController extends BaseController<PatientVisit> implements Observer {
    
    private VisitDbManager manager = new VisitDbManager();
    @FXML
    ListView<PatientVisit> listviewVisitList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager.addObserver(this);
    }

    @FXML
    private void btnViewVisit_OnAction(ActionEvent event) {
        if (target == null || target.getVisitId() == 0){
            AlertHelper.ShowWarning("Error", null, "Please select a visit.");
            return;
        }
        ShowVisitDialog(target);
    }

    @FXML
    private void btnAddVisit_OnAction(ActionEvent event) {
        ShowVisitDialog(null);
    }

    private void ShowVisitDialog(PatientVisit visitToDisplay) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Dialogs.VisitModify.toString()));

        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((AnchorPane) loader.load()));

            BaseController controller = loader.<FXMLPatientVisitController>getController();
            controller.initData(patient, visitToDisplay, manager);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            AlertHelper.ShowError("Dialog Error", "Error opening PatientVisit dialog", e);
        }
    }

    @FXML
    private void listviewVisitList_OnMouseClicked(MouseEvent event) {
        target = listviewVisitList.getSelectionModel().getSelectedItem();
    }

    @Override
    public ValidationStatus validateForm() {
        // NO-OP
        return null;
    }

    @Override
    public void saveToDatabase() {
        // NO-OP
    }

    @Override
    public void populateData() {
        listviewVisitList.getItems().clear();
        listviewVisitList.getItems().addAll(dbManager.getList(patient.getId()));
    }

    @Override
    public void reset() {
        // NO-OP
    }

    @Override
    public void update(Observable o, Object arg) {
        populateData();
    }
}
