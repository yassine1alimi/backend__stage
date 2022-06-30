package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.UserInformation;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUtilidateurRepo extends JpaRepository <UserInformation, Long> {
	
Optional<UserInformation> findByUsername (String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	Optional<UserInformation> findByEmail(String email);
	
	@Query("select u from UserInformation u where u.archived = 1")
	List<UserInformation> getEmployes ();
	
	@Query("select u from UserInformation u where u.archived = 2")
	List<UserInformation> getArchivedEmployes ();
   
	@Query("select emp from UserInformation emp where emp.departement =:departement")
	List<UserInformation> getAllEmployesByDepartement(@Param("departement") String departement);
}
	
	





 
