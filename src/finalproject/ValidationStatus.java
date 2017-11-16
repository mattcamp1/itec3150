package finalproject;

import java.util.*;

/**
 * Class: ValidationStatus
 *
 * @author Matthew Camp Version 1.0 Course: Advanced Programming Fall 2017
 * Written: , 2017 Holds validation information and errors for the user to see
 * when attempting to submit the form
 */
public class ValidationStatus {

	private Stack<String> fieldsWithErrors;
	private boolean isValid;

	public ValidationStatus() {
		fieldsWithErrors = new Stack<String>();
		isValid = true;
	}

	public void addFieldError(String fieldName) {
		fieldsWithErrors.push(fieldName);
		isValid = false;
	}

	public String getErrors() {
		StringBuilder errors = new StringBuilder();

		while (!fieldsWithErrors.isEmpty()) {
			errors.append(fieldsWithErrors.pop());
			if (!fieldsWithErrors.isEmpty()) {
				errors.append(", ");
			}
		}
		return errors.toString();
	}

	public boolean getIsValid() {
		return isValid;
	}
}
