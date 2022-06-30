package com.sofrecom.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Departement;
import com.sofrecom.stage.models.Employe;


@Repository
public interface IEmployeRepo extends JpaRepository<Employe, Long> {
	
	//public Employe findEmployeById(Long id ); 
	@Query("SELECT count(*) FROM Employe")
    public int countuser();
	
	 @Query("SELECT username FROM Employe")
	    public List<String> userNames();
	 
	 
	 
	 
	 
	 
	 @Query("Select "
				+ "DISTINCT emp from Employe emp "
				+ "join emp.groupes groupes "
				+ "join groupes.departement depart "
				+ "where depart=:departement")
	    public List<Employe> getAllEmployeByDepartementc(@Param("departement") Departement departement);

}
