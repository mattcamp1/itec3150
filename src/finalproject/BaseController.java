package finalproject;

import finalproject.database.Patient;
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

	protected BaseController parent;
	protected Patient patient;
	protected T target;

	public void initData(BaseController parent, Patient patient, T target) {
		this.parent = parent;
		this.patient = patient;
		this.target = target;

		populateData();
	}
}
