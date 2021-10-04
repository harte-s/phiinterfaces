package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Physician extends User implements PHIAdminCompliant, PHICompliantUser {
    
    private HashSet<Patient> patients;
    private ArrayList<String> securityIncidents;
    
    // ... you see other existing member variables. ...
    

    public void prescribeRXTo(Patient patient, Integer rxNumber) {
        patient.currentPrescriptionsByRX.add(rxNumber);
    }
    
    
    public Physician(HashSet<Patient> patients, ArrayList<String> securityIncidents) {
		super();
		this.patients = patients;
		this.securityIncidents = securityIncidents;
	}


	public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
    
	@Override
	public boolean assignPin(int pin) {
		boolean isLongEnough = false;
		if(pin < 1000000 && pin >= 100000) {
			isLongEnough = true;
		}
		return isLongEnough;
	}

	@Override
	public boolean isAuthorized(Integer confirmedAuthID) {
		boolean match = false;
		if (confirmedAuthID.equals(this.id)) {
			match = true;
			this.newIncident("match success");
		}
		else {
			this.authIncident();
		}
		return match;
	}

	@Override
	public ArrayList<String> reportSecurityIncidents() {
		ArrayList<String> fullReport = this.getSecurityIncidents();
		return fullReport;
	}
   
 
    public HashSet<Patient> getPatients() {
		return patients;
	}


	public void setPatients(HashSet<Patient> patients) {
		this.patients = patients;
	}


	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}


	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}




}
