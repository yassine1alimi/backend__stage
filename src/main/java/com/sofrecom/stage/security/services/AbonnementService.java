package com.sofrecom.stage.security.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.Abonnement;
import com.sofrecom.stage.repository.AbonnementRepository;



@Service

public class AbonnementService {
	
	@Autowired
	private AbonnementRepository abonnementRepository;
	
	
	
	public Abonnement addAbonnement(Abonnement abonnement) {
		
		return abonnementRepository.saveAndFlush(abonnement);
		
	}
	
	public void updateAbonnement(Abonnement abonnement) {
		
			
		    Abonnement a = abonnementRepository.getById(abonnement.getIdAbonnement());	
		
			if(abonnement.getIdUser() != 0)
			a.setIdUser(abonnement.getIdUser());
			
			if(abonnement.getPays() != null)
			a.setPays(abonnement.getPays());
			
			if(abonnement.getDateDebut() != null)
			a.setDateDebut(abonnement.getDateDebut());
			
			if(abonnement.getDateFin() != null)
			a.setDateFin(abonnement.getDateFin());
			
			abonnementRepository.saveAndFlush(a);

			
		
			
		
	}
	public void updateAbonnement2(Long id) {
		
		
	    Abonnement a = abonnementRepository.getById(id);	
	
	    a.setEtat(true);
				abonnementRepository.save(a);

		
	
		
	
}

	
	public void deleteAbonnement(Long id) {
		abonnementRepository.deleteById(id);
	}
	
	public List<Abonnement> retrieveAllAbonnements() {
		List<Abonnement> abonnement = (List<Abonnement>) abonnementRepository.findAll();
		return abonnement;
	}
	public List<Abonnement> getAbonnementByPays(String pays) {
		List<Abonnement> abonnement = abonnementRepository.findByPays(pays);
		return abonnement;
	}
	
	public Abonnement retrieveAbonnement(long id) {
		Abonnement a = null;
		try {
			a =abonnementRepository.findById(id).get();
		}
		catch(NoSuchElementException e) {
			return null;
		}
		return a;
	}
	
	
	
	/*public int stat (String p)
	{List<Abonnement> abonnement = (List<Abonnement>) abonnementRepository.findAll();
	int nb=0;
	for(Abonnement i :abonnement) {if(i.getPays()==p) {
		nb++;
	}
		
	}
	return nb;
	}*/
	/*
	public List<String> retrieveAllPays() {
		List<Abonnement> abonnement = (List<Abonnement>) abonnementRepository.findAll();
		int n = abonnement.size();
		List<String> paysList = new ArrayList<String>();
		for (int i=0; i < n; i++) {
			if(abonnement.get(i).getPays()==abonnement.get(i+1).getPays()) {
				paysList.add(abonnement.get(i).getPays());
				
			}
			
		}
		return paysList;
		}
	
	public List<String> stat (Abonnement abonnement) {
		int nb=0;
		List<String> paysList = new ArrayList<String>();
		paysList = retrieveAllPays();
		for (String p:paysList) { 
			if (p == abonnement.getPays()) {
				abonnement.setNbPays(nb+1);
				
			}
			
		}
		
		return paysList.stream().sorted(Comparator.comparing(Abonnement::getNbPays).reversed())
				.collect(Collectors.toList());
	}
	*/
	}
	
	

