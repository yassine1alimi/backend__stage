package com.sofrecom.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Abonnement;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
public List<Abonnement> findByPays(String pays);
}
