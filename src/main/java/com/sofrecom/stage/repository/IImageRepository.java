package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofrecom.stage.models.Image;


public interface IImageRepository extends JpaRepository<Image, Long> {
	
}
