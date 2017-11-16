package finalproject;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;


/**
 * @author jnbcb
 */
public class FinalProject extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLPatientSummary.fxml"));
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);

		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
