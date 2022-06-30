package com.sofrecom.stage.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Demande;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IDemandeRepo;
import com.sofrecom.stage.repository.IUtilidateurRepo;
import com.sofrecom.stage.services.DemandeServiceImpl;
import com.sofrecom.stage.services.EmployeServiceImpl;
/*
import com.HRPlus.space.entities.Conge;
import com.HRPlus.space.entities.UserInformation;
import com.HRPlus.space.repositories.ICongeRepo;
import com.HRPlus.space.repositories.IUtilidateurRepo;
import com.HRPlus.space.services.CongeServiceImpl;
import com.HRPlus.space.services.EmployeServiceImpl;
*/
@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class DemandeController {
	
	@Autowired
	private DemandeServiceImpl demandeService ;
	
	@Autowired
	private IUtilidateurRepo userRepo ;
	
	
	
	@Autowired
	private IDemandeRepo demandeRepo ; 
	
	private EmployeServiceImpl employeService;
	
	@GetMapping("/demandes")
	public List<Demande> getAllDemande(){
		return demandeRepo.findAll();
				
	}
	
	@PostMapping("/createDemande")
	public Demande createDemande ( @Valid @RequestBody Demande demande, @RequestParam Long id ) {
		
		UserInformation user = userRepo.findById(id).get();
		demande.setUser(user);
		return demandeRepo.save(demande);
	}
	
	@GetMapping("/demande/{id}")
	public ResponseEntity<Demande> findById(@PathVariable("id") Long id) {
		Optional<Demande> demande = demandeRepo.findById(id);
		if (demande.isPresent())
			return new ResponseEntity<Demande>(demande.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Demande>(HttpStatus.NO_CONTENT);

	}
	
	@GetMapping("/demandeUser/{id}")
	public List<Demande> getDemandesByIdUser(@PathVariable("id") Long id){
		
		UserInformation user = userRepo.findById(id).get();
		return user.getDemandes();
		
	}
	
	@GetMapping("/accepter")
	public void accepterDemande( @RequestParam Long id) {
	
      demandeService.accepterDemande(id);
		
}
	
	@GetMapping("/refuser")
	public void refuserDemande (@RequestParam Long id) {
		
		demandeService.refuserDemande(id);
	}
	
	@DeleteMapping("/deleteDemande/{id}")
	public ResponseEntity<Demande> deleteDemande (@PathVariable("id") Long id) {
		Optional<Demande> demande = demandeRepo.findById(id);
		if (demande.isPresent()) {
			demandeRepo.delete(demande.get());
			return new ResponseEntity<Demande>(demande.get(), HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<Demande>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@GetMapping("demandes_attente")
	public List<Demande> getDemandeByStatus (){
		return demandeRepo.getDemandeByStatus();
	}
	/*
	@GetMapping("/dureeDemande/{id}")
	public int getDuree(@PathVariable Long id ) {
		return demandeService.countDuree(id);
	}
	*/
	/*
	@GetMapping("/soldeDemande/{id}")
	public int getSoldeDemande(@PathVariable Long id ) {
		UserInformation user = userRepo.findById(id).get();
		return user.getJoursDemandes() - demandeService.countDuree(id);
	}
	
	*/
	
	
}

