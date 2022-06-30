package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.NoteInterne;


@Repository
public interface NoteInterneRepo extends JpaRepository<NoteInterne, Long> {

}
