package com.sofrecom.stage.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Timesheet implements Serializable{

	private static final long serialVersionUID = 3876346912862238239L;

	@EmbeddedId
	private TimesheetPK timesheetPK;
	
	//idMission est a la fois primary key et foreign key
	/*@ManyToOne
    @JoinColumn(name = "idReclamation")
	private ReclamationClient reclamationClient;
*/	
	
	@ManyToOne
    @JoinColumn(name = "idReclamation", insertable=false, updatable=false)
	private ReclamationClient reclamationClient;
	
	
	
	
	
	
	
	
	
	//idEmploye est a la fois primary key et foreign key
	
	@ManyToOne
    @JoinColumn(name = "idUser", insertable=false, updatable=false)
	private Employe employe;
	
	
	private boolean isValide;

	

	public boolean isValide() {
		return isValide;
	}

	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}

	
	
	
}
