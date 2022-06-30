package com.sofrecom.stage.services;

import java.util.List;

import com.sofrecom.stage.models.Departement;
import com.sofrecom.stage.models.Groupe;

public interface IDepartementService {
	
	public int ajouterDepartement(Departement departement);

	public int ajouterGroupe(Groupe groupe);
	public int affecterGroupeADepartement(int groupeId, int departementId);
	List<String> getAllGroupesNamesByDepartement(int departementId);

	public Departement getDepartementById(int departementId);
	public Groupe getGroupeById(int groupeId);

	public int deleteDepartementById (int departementId);
	public int deleteGroupeById(int groupeId);
	

}
