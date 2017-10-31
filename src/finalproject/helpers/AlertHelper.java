package finalproject.helpers;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;


/**Class: AlertHelper
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Helper class to make showing alerts in JavaFX easier
 *
 */
public class AlertHelper {

	public static void ShowInfo(String title, String header, Object content) {
		ShowAlert(Alert.AlertType.INFORMATION, title, header, content);
	}

	public static void ShowWarning(String title, String header, Object content) {
		ShowAlert(Alert.AlertType.WARNING, title, header, content);
	}

	public static void ShowError(String title, String header, Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		String stackTrace = sw.toString();
		ShowAlert(Alert.AlertType.ERROR, title, header, stackTrace);
	}

	private static void ShowAlert(Alert.AlertType type, String title, String header, Object content) {
		Alert alert = new Alert(type);
		alert.setResizable(false);
		alert.setHeaderText(header);
		alert.setTitle(title);
		alert.setContentText(content.toString());
		alert.showAndWait();
	}
}
