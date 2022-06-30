package com.sofrecom.stage.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sofrecom.stage.models.Demande;
import com.sofrecom.stage.models.Departement;
import com.sofrecom.stage.models.Employe;
import com.sofrecom.stage.models.Groupe;
import com.sofrecom.stage.repository.GroupeRepository;
import com.sofrecom.stage.repository.IEmployeRepo;



@Service
public class EmployeServiceImpl  {

	@Autowired
	private IEmployeRepo employeRepo;
	@Autowired
	GroupeRepository groupeRepoistory;
	
	public List<Employe> getAllEmployees() {
		return employeRepo.findAll();
	}

	
	public boolean saveEmploye(Employe employe) {

		if (employeRepo.save(employe) != null)
			return true;
		else
			return false;
	}

	public void addEmploye(Employe employe) {
	
		 employeRepo.save(employe);
	}
	
	
	
	
	public void deleteEmploye(Employe employe) {
		if (employe != null)
			employeRepo.delete(employe);

	}

	
	public Employe updateEmploye(Employe employe) {
		return employeRepo.save(employe);
	}

	
	public Long countEmploye() {

		return employeRepo.count();

	}

	
	public Optional<Employe> findById(Long id) {

		return employeRepo.findById(id);

	}
	
	public List<Demande> getDemandeByemployeId (Long id){
		
		Employe employe = new Employe();
		if (employe.getDemandes() != null)
			return employe.getDemandes();
		else 
			return null ;
		
	}

	
	
	
	
	
	@Transactional	
	public void affecterEmployeAGroupe(Long employeId, int groupeId) {
				//l.info("START affecterEmployeAGroupe with employeId : "+employeId + "and groupeId : "+groupeId);

		Groupe groupeManagedEntity = groupeRepoistory.findById(groupeId).get();
		Employe employeManagedEntity = employeRepo.findById(employeId).get();
			//l.trace("Début Test : verifier si le groupe na aucun employe");

		if(groupeManagedEntity.getEmployes() == null){
			//l.trace("Entrer Test : le groupe na aucun employe");
			List<Employe> employes = new ArrayList<Employe>();
			employes.add(employeManagedEntity);
			groupeManagedEntity.setEmployes(employes);
		}else{
			//l.trace("Entrer Test : le groupe a des employes");
			groupeManagedEntity.getEmployes().add(employeManagedEntity);
		}

		// à ajouter? 
		groupeRepoistory.save(groupeManagedEntity); 

	}
	
	
	
	public List<Employe> getAllEmployeByDepartement(Departement departement) {
		//l.info("Starting getAllEmployeByDepartement");
		return employeRepo.getAllEmployeByDepartementc(departement);
	}
	
}
