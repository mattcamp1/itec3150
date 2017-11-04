package finalproject.database;

/**
 * Class: Patient
 *
 * @author Jarrod Bailey
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * This is the patient class to store patient information. Do not use setId outside of databaseManager
 */
public class Patient {

	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	private String email; // maybe check here for format
	private String dob;
	private String maritalStatus;
	private String insurance;

	public Patient(String name, String address, String phoneNumber,
				   String email, String dob, String maritalStatus, String insurance) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dob = dob;
		this.maritalStatus = maritalStatus;
		this.insurance = insurance;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	// change for listview
	@Override
	public String toString() {
		return String.valueOf(this.getName() + " " + this.getDob() + " " + this.getInsurance());
	}

}
