package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Administrateur;


@Repository
public interface IAdminRepo extends JpaRepository<Administrateur, Long> {

}
