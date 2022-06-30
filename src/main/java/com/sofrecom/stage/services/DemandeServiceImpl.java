package com.sofrecom.stage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.Demande;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IDemandeRepo;
import com.sofrecom.stage.repository.IUtilidateurRepo;



@Service
public class DemandeServiceImpl  {
	
	private int d=0;
	@Autowired
	private IDemandeRepo demandeRepo ; 
	
	@Autowired
	private IUtilidateurRepo userRepo ;
	 
	
	public List<Demande> getAllDemandes (Demande demande){
		return demandeRepo.findAll(); 
	}
	public Optional<Demande> findDemandeById (Long id) {
		return demandeRepo.findById(id);
	}
	
	public Demande createDemande (Demande demande) {
		demande.setStatusOfDemand("not_yet_treated");
		return demandeRepo.save(demande);
	}
	
	public Demande updateDemande (Demande demande) {
		return demandeRepo.save(demande);
		
	}
	
	public List<Demande> getAllDemandes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Demande CreateDemande(Demande demande) {
		// TODO Auto-generated method stub
		return null;
	}
	public void deleteDemande(Demande demande) {
		demandeRepo.delete(demande);
		
	}
	
	
	
//	public void accepterConge(Long id) {
//		Optional<Conge> conge = congeService.findCongeById(id);
//		if (conge.isPresent()) {
//			
//			conge.get().setStatusOfDemand(conge.get().getStatusOfDemand().ACCEPTED);
//			congeRepo.save(conge.get());
//		}
//	}
	
	public void accepterDemande(Long id) {
		
		Demande demande = demandeRepo.findById(id).get();
		 	UserInformation user = demande.getUser();
		 	
		 	demande.setStatusOfDemand("Accepted");
		 	demandeRepo.save(demande);
			//user.setJoursConges(user.getJoursConges() + conge.getDuree());
			//user.setSoldeConges(user.getDureeConges() - user.getJoursConges());
			userRepo.save(user);
		 	
			
		}
	
	
	public boolean refuserDemande (Long id) {
		
		Optional<Demande> demande = demandeRepo.findById(id);
		if (demande.isPresent()) {
			demande.get().setStatusOfDemand("Refused");
			demandeRepo.save(demande.get());
		return true ;
		}
		return false ;
	}
	/*
	public int countDuree (Long id ) {
		
		UserInformation user = userRepo.findById(id).get();
		List<Conge> conges = user.getConges();
		for (Conge conge:conges) {
			d = d + (conge.getDuree());
		}
		return d; 
	}
	*/
	/*
	public void updateDuree (Long id ) {
		
		UserInformation user = userRepo.findById(id).get();
		user.setDureeConges(d);
		userRepo.save(user);
	}
	*/

}