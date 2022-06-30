package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Departement;
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {

}
