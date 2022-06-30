package com.sofrecom.stage.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Abonnement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAbonnement;

	
	private String pays;
	private Boolean etat;

	
	private long idUser;

	
	private Date dateDebut;
    
	
	private Date dateFin;
	
	@Transient
	private int NbPays;


	public int getNbPays() {
		return NbPays;
	}


	public void setNbPays(int nbPays) {
		NbPays = nbPays;
	}


	public Long getIdAbonnement() {
		return idAbonnement;
	}


	public void setIdAbonnement(Long idAbonnement) {
		this.idAbonnement = idAbonnement;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long l) {
		this.idUser = l;
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


	public Abonnement(Long idAbonnement, String pays, Integer idUser, Date dateDebut, Date dateFin) {
		super();
		this.idAbonnement = idAbonnement;
		this.pays = pays;
		this.idUser = idUser;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}


	public Abonnement(Long idAbonnement, String pays, Boolean etat, long idUser, Date dateDebut, Date dateFin,
			int nbPays) {
		super();
		this.idAbonnement = idAbonnement;
		this.pays = pays;
		this.etat = etat;
		this.idUser = idUser;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		NbPays = nbPays;
	}


	public Boolean getEtat() {
		return etat;
	}


	public void setEtat(Boolean etat) {
		this.etat = etat;
	}


	public Abonnement() {
		super();
	}
	
	
	

}
