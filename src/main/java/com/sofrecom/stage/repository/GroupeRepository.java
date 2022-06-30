package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Groupe;
@Repository

public interface GroupeRepository extends JpaRepository<Groupe, Integer> {

}
