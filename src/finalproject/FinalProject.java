/*
 */
package finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jnbcb
 */
public class FinalProject extends Application {

    private static Stage mainStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPatientSummary.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        mainStage = stage;

        stage.show();
    }

    public static Stage getMainInstance() {
    	return mainStage;
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
