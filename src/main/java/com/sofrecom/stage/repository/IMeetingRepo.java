package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Meeting;


@Repository
public interface IMeetingRepo extends JpaRepository<Meeting, Long> {

}
