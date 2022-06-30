package com.sofrecom.stage.services;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.Demande;
import com.sofrecom.stage.models.ReclamationClient;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IReclamationClientRepo;
import com.sofrecom.stage.repository.IUtilidateurRepo;


@Service
public class ReclamationClientService {
	
	@Autowired
	private IReclamationClientRepo reclamationclientRepo ; 
	@Autowired
	private IUtilidateurRepo userRepo ;
	
	private final Path fileStorageLocation = Paths.get("C:\\Users\\STRIX\\Downloads1");

	public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = (Resource) new UrlResource(filePath.toUri());
            if(((AbstractFileResolvingResource) resource).exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }
	
	
	
	public Long Number_reclamation() {
		Long sum=(long) 0;
			List<ReclamationClient> liste_reclamation = (List<ReclamationClient>) reclamationclientRepo.findAll();
			for(ReclamationClient r:liste_reclamation) {
				sum=sum+1;
			}
			return sum;
		}

	
	public Long Etat_reclamation(String etat) {
		Long sum=(long) 0;
		List<ReclamationClient> liste_reclamation = (List<ReclamationClient>) reclamationclientRepo.findAll();
		for(ReclamationClient r:liste_reclamation) {
			if(r.getStatusOfDemand()==etat)
			sum=sum+1;
		}
		return sum;
	}
	public Long Type_reclamation( String typeClaim) {
		Long sum=(long) 0;
		List<ReclamationClient> liste_reclamation = (List<ReclamationClient>) reclamationclientRepo.findAll();
		for(ReclamationClient r:liste_reclamation) {
			if(r.getTypeClaim()==typeClaim)
			sum=sum+1;
		}
		return sum;
	}
	
	
	public Long Departement_reclamation( String departement) {
		Long sum=(long) 0;
		List<ReclamationClient> liste_reclamation = (List<ReclamationClient>) reclamationclientRepo.findAll();
		for(ReclamationClient r:liste_reclamation) {
			if(r.getEmploye().getDepartement()==departement)
			sum=sum+1;
		}
		return sum;
	}
	
	
public void accepterReclamationClient(Long id) {
		
		ReclamationClient reclamation = reclamationclientRepo.findById(id).get();
		 	
		 	reclamation.setStatusOfDemand("Accepted");
		 	reclamationclientRepo.save(reclamation);
			//user.setJoursConges(user.getJoursConges() + conge.getDuree());
			//user.setSoldeConges(user.getDureeConges() - user.getJoursConges());
		}
	
	
public boolean refuserReclamationClient (Long id) {
	
	Optional<ReclamationClient> reclamationclient = reclamationclientRepo.findById(id);
	if (reclamationclient.isPresent()) {
		reclamationclient.get().setStatusOfDemand("Refused");
		reclamationclientRepo.save(reclamationclient.get());
	return true ;
	}
	return false ;
}
	


public List<ReclamationClient> getAllReclamationClients (ReclamationClient reclamationClient){
	return reclamationclientRepo.findAll(); 
}
public Optional<ReclamationClient> findReclamationClientById (Long id) {
	return reclamationclientRepo.findById(id);
}
}

