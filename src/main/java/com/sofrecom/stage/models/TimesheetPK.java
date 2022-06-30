package com.sofrecom.stage.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class TimesheetPK implements Serializable {

	private static final long serialVersionUID = 5377539445871317492L;

	private Long idReclamation;
	
	private Long idUser;
	
	//Choisir le TemporalType selon le besoin metier
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	public Long getIdReclamation() {
		return idReclamation;
	}

	public void setIdReclamation(Long idReclamation) {
		this.idReclamation = idReclamation;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public TimesheetPK() {
		super();
	}

	public TimesheetPK(Long idReclamation, Long idUser, Date dateDebut, Date dateFin) {
		super();
		this.idReclamation = idReclamation;
		this.idUser = idUser;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	
	//Pour que hibernate peut comparer deux objets (par exemple : recherche de l'objet dans le persistenceContext), 
		//Il doit pouvoir comparer les primary key des deux entites
		//Vu que l'entite a une clé composé, on doit implementer la methode equal.
		//Utiliser l'IDE pour générer le equal et le hashcode
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TimesheetPK other = (TimesheetPK) obj;
			if (dateDebut == null) {
				if (other.dateDebut != null)
					return false;
			} else if (!dateDebut.equals(other.dateDebut))
				return false;
			if (dateFin == null) {
				if (other.dateFin != null)
					return false;
			} else if (!dateFin.equals(other.dateFin))
				return false;
			if (idUser != other.idUser)
				return false;
			if (idReclamation != other.idReclamation)
				return false;
			return true;
		}

	

	
}
