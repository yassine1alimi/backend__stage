package com.sofrecom.stage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Demande;
import com.sofrecom.stage.models.ReclamationClient;


@Repository
public interface IReclamationClientRepo extends JpaRepository<ReclamationClient, Long> {
    //List<ReclamationClient> findByUserId(Long id);
	//Optional<ReclamationClient> findByIdUser(Long id);
//	@Query("select c from Reclamation c where c.nom !=null")
//	List<Reclamation> getNonClient();
	
	@Query("select c from ReclamationClient c where c.statusOfDemand like 'Waiting'")
	public List<ReclamationClient> getReclamationClientByStatus();
}
