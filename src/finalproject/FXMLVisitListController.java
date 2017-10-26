/*
 */
package finalproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author jnbcb
 */
public class FXMLVisitListController implements Initializable {

    @FXML
    private ListView<?> visitListView;
    @FXML
    private Button viewVisitButton;
    @FXML
    private Button addViewButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewVisit(ActionEvent event) {
    }

    @FXML
    private void addVisit(ActionEvent event) {
    }
    
}
