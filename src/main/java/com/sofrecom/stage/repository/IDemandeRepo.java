package com.sofrecom.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Demande;

@Repository
public interface IDemandeRepo extends JpaRepository<Demande, Long> {
	@Query("select c from Demande c where c.statusOfDemand like 'Waiting'")
	public List<Demande> getDemandeByStatus();
	/*
	@Query("select count(duree) from Conge c where c.user.idUser = :id")
	public int countDuree(Long id );
	*/
}
