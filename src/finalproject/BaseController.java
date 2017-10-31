package finalproject;

import finalproject.database.*;
import finalproject.helpers.AlertHelper;
import javafx.fxml.Initializable;


/**Class: BaseController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Base class for any controllers
 *
 */
public abstract class BaseController<T> implements Initializable, IValidation<T> {

	protected DatabaseManager<T> dbManager;
	protected BaseController parent;
	protected Patient patient;
	protected T target;

	public void initData(BaseController parent, Patient patient, T target, DatabaseManager<T> dbManager) {
		this.parent = parent;
		this.patient = patient;
		this.target = target;
                this.dbManager = dbManager;

		if (target instanceof Allergy) {
			//dbManager = (DatabaseManager<T>) new AllergyDbManager();
		} else if (target instanceof Medication) {
			//dbManager = (DatabaseManager<T>) new MedicationDbManager();
		} else if (target instanceof PatientVisit) {
			//dbManager = (DatabaseManager<T>) new VisitDbManager();
		} else {
			AlertHelper.ShowWarning("Database Manager Error", null, "An improper database manager was supplied by the parent form");
		}

		populateData();
	}
}
