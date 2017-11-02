package finalproject;

import finalproject.database.*;
import finalproject.helpers.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Class: BaseController
 *
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Base class for any controllers
 */
public abstract class BaseController<T> implements Initializable, IValidation {

	@FXML
	private Button btnSave;

	@FXML
	private Button btnReset;

	@FXML
	protected AnchorPane anchor;

	protected DatabaseManager<T> dbManager;
	protected Patient patient;
	protected T target;

	public  void initData(Patient patient, T target, DatabaseManager<T> dbManager) {
		this.patient = patient;
		this.target = target;
		this.dbManager = dbManager;

		try {
			populateData();
		} catch (Exception e) {
			AlertHelper.ShowError("Error loading data", "There was an error loading the requested data", e);
		}
	}

	@FXML
	protected void btnSave_OnAction(ActionEvent event) {
		ValidationStatus status = validateForm();

		if (status.getIsValid()) {
			saveToDatabase();
			((Stage) anchor.getScene().getWindow()).close();
		} else {
			AlertHelper.ShowWarning("Validation Error", null, validateForm().getErrors());
		}
	}

	@FXML
	protected void btnReset_OnAction(ActionEvent event) {
		reset();
	}
}
