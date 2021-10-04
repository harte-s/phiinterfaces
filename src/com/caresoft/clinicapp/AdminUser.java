package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements PHIAdminCompliant, PHICompliantUser {
    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;
    
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
		if (confirmedAuthID.equals(this.getEmployeeID())) {
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
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}
	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}

}
