package finalproject;


/**Interface: IValidation
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Interface for any controller classes that need validation
 *
 */
public interface IValidation {

	/**
	 * Validates that all fields in the form are not empty and contain properly formatted data.
	 * In the real world, this could also be used to sanitize data before sending it to the database.
	 * @return The outcome of validation testing
	 */
	ValidationStatus validateForm();

	void saveToDatabase();

	void populateData();

	void reset();
}
