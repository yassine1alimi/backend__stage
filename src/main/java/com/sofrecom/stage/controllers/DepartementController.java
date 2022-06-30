package com.sofrecom.stage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Departement;
import com.sofrecom.stage.models.Groupe;
import com.sofrecom.stage.models.Todo;
import com.sofrecom.stage.repository.DepartementRepository;
import com.sofrecom.stage.repository.GroupeRepository;
import com.sofrecom.stage.services.IDepartementService;
import com.sofrecom.stage.services.UserService;



@CrossOrigin
@RestController
public class DepartementController {
	
	
	@Autowired
	UserService userservice;
	@Autowired
	IDepartementService idepartementservice;
	@Autowired
	DepartementRepository departRepo;
	
	
	@Autowired
	GroupeRepository groupeRepo;
	
	@GetMapping("/departements")
    public List<Departement> getAllDepartements() {
        return (List<Departement>) departRepo.findAll();
    }
	
	
	// Ajouter departement : http://localhost:8087/SpringMVC/servlet/ajouterDepartement
	//{"id":1,"name":"II Consulting","raison":"tfdgdfgdc"}

	@PostMapping("/ajouterDepartement")
	@ResponseBody
	public int ajouterDepartement(@RequestBody Departement departement) {
		return idepartementservice.ajouterDepartement(departement);
	}
	@GetMapping("/groupes")
    public List<Groupe> getAllGroupes() {
        return  groupeRepo.findAll();
    }
	
	
	 @PostMapping("/ajouterGroupe")
	 	@ResponseBody
		public int ajouterGroupe(@RequestBody Groupe groupe) {
			return idepartementservice.ajouterGroupe(groupe);
		}
	
	 @DeleteMapping("/groupes/{id}")
		void deleteNote(@PathVariable int id) {
		 groupeRepo.deleteById(id);
		  }
	

	// http://localhost:8081/SpringMVC/servlet/affecterDepartementAEntreprise/1/1
    @PutMapping(value = "/affecterGroupeADepartement/{idgroupe}/{iddepartement}") 
	public void affecterGroupeADepartement(@PathVariable("idgroupe")int groupeId, @PathVariable("iddepartement")int departementId) {
    	idepartementservice.affecterGroupeADepartement(groupeId, departementId);
	}
	
    
   
    
	 // http://localhost:8087/SpringMVC/servlet/getAllGroupesNamesByDepartement/1
    @GetMapping(value = "getAllGroupesNamesByDepartement/{iddepartement}")
    @ResponseBody
	public List<String> getAllGroupesNamesByDepartement(@PathVariable("iddepartement") int departementId) {
		return idepartementservice.getAllGroupesNamesByDepartement(departementId);
	}
    
    
 // http://localhost:8081/SpringMVC/servlet/deleteEntrepriseById/1
    @DeleteMapping("/deleteDepartementById/{iddepartement}") 
	@ResponseBody 
	public void deleteDepartementById(@PathVariable("iddepartement")int departementId)
	{
		idepartementservice.deleteDepartementById(departementId);
	}
  

    
 // http://localhost:8081/SpringMVC/servlet/getEntrepriseById/1
    @GetMapping(value = "getDepartementById/{iddepartement}")
    @ResponseBody
	public Departement getDepartementById(@PathVariable("iddepartement") int departementId) {

		return idepartementservice.getDepartementById(departementId);
	}
    
    
    
    
 // URL : http://localhost:8081/SpringMVC/servlet/deleteDepartementById/3
    @DeleteMapping("/deleteGroupeById/{idgroupe}") 
	@ResponseBody 
	public void deleteGroupeById(@PathVariable("idgroupe") int groupeId) {
		idepartementservice.deleteGroupeById(groupeId);

	}
}
