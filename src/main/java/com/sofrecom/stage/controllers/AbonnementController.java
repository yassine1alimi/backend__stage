package com.sofrecom.stage.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Abonnement;
import com.sofrecom.stage.models.Stat;
import com.sofrecom.stage.security.services.AbonnementService;



@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AbonnementController {
	
	@Autowired
	private AbonnementService abonnementService;
	
	//@Autowired
	//EmailService emailService;
	
	@PostMapping("/add")
	public Abonnement addAbonnement(@RequestBody Abonnement abonnement) {
		abonnement.setEtat(false);
		return abonnementService.addAbonnement(abonnement);
		
	}
	
	@PostMapping("update")
	public void updateAbonnement(Abonnement abonnement) {
		
		abonnementService.updateAbonnement(abonnement);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<HttpStatus> deleteAbonnement (@PathVariable("id") Long id) {
		
		abonnementService.deleteAbonnement(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/byid/{id}")
    public Abonnement retrieveClient(@PathVariable("id") Long abonnementId) {
        return abonnementService.retrieveAbonnement(abonnementId);
    }
	
	@GetMapping("/getAll")
    public List<Abonnement> getAbonnements() {
	        return abonnementService.retrieveAllAbonnements();
    }
	@GetMapping("/getStat")
    public List<Stat> getAbonnementsStat() {
		List<Stat>stats=new ArrayList<Stat>();
		
		int sum=0;
		List<Abonnement>abs= abonnementService.retrieveAllAbonnements();
		for(Abonnement ab:abs) {
			
			List<Abonnement>abss=abonnementService.getAbonnementByPays(ab.getPays());
			for(Abonnement ab1:abss) {
				sum=sum+1;
				Stat stat=new Stat(sum, ab.getPays());
				stats.add(stat);
			}
		}
		return stats;
    }
	
	@GetMapping("/acceptAbonnement/{id}")
	public ResponseEntity<HttpStatus> acceptAbonnement(@PathVariable("id") Long id) {
		Abonnement a = abonnementService.retrieveAbonnement(id);
		abonnementService.updateAbonnement2(id);
		/*StringBuilder str
        = new StringBuilder();
		str.append("Hello,");
		str.append("Your Subscription with id:"+ a.getIdAbonnement() + " is accepted,");
		
		emailService.sendSimpleMessage("eya.raddaoui@esprit.tn", "Demande Accepted", str.toString());
		*/
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
