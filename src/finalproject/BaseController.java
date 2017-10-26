package finalproject;

import finalproject.database.Patient;
import javafx.fxml.Initializable;

public abstract class BaseController<T> implements Initializable {

	public abstract void initData(Patient patient, T target);
}
