package finalproject.database;

import java.util.*;


/**
 * Class:
 *
 * @author Jarrod Bailey
 * Version 1.0
 * Course: ITEC 2140 Spring 2017 Section 6
 * Written: , 2017
 */
public class VisitDbManager extends Observable implements DatabaseManager<PatientVisit> {

	/**
	 * Inserts a patients visit into database
	 * visit must have patient ID
	 *
	 * @param visit PatientVisit object
	 * @return boolean true if successful
	 */
	@Override
	public boolean insert(PatientVisit visit) {
		VisitDb manager = new VisitDb();
		boolean success = manager.insertVisit(visit);
		manager.close();
		setChanged();
		notifyObservers();
		return success;
	}

	/**
	 * Gets a specific PatientVisit object
	 *
	 * @param visitId id of visit to be retrieved
	 * @return PatientVisit
	 */
	@Override
	public PatientVisit get(int visitId) {
		VisitDb manager = new VisitDb();
		PatientVisit visit = manager.getVisit(visitId);
		manager.close();
		return visit;
	}

	/**
	 * Used to get list of visits for a specific patient. Used to populate list view
	 *
	 * @param patientId id of patient
	 * @return List of visits
	 */
	@Override
	public List<PatientVisit> getList(int patientId) {
		VisitDb manager = new VisitDb();
		List<PatientVisit> visitList = manager.getPatientVisitList(patientId);
		manager.close();
		return visitList;
	}

	@Override
	public boolean update(PatientVisit visit) {
		VisitDb manager = new VisitDb();
		boolean success = manager.updateVisit(visit);
		manager.close();
		setChanged();
		notifyObservers();
		return success;
	}

	@Override
	public boolean delete(int vId) {
		VisitDb manager = new VisitDb();
		boolean success = manager.deleteVisit(vId);
		manager.close();
		setChanged();
		notifyObservers();
		return success;
	}
}
