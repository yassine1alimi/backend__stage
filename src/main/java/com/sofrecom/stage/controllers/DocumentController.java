package com.sofrecom.stage.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Document;
import com.sofrecom.stage.repository.IDocumentRepo;



@RestController

public class DocumentController {
	

	@Autowired
	private IDocumentRepo documentRepo ;
	
	@GetMapping("/document/{id}")
	public List<Document> findDocumentByUserId(@PathVariable Long id ) {
		return documentRepo.findDocumentByUserId(id);
	}

	@GetMapping("/document/titre/{titre}")
	public List<Document> findDocumentByTitle(@PathVariable String titre){
		return documentRepo.findDocumentByTitle(titre);
	}
	
	@GetMapping("/document/type/{type}")
	public List<Document> findByDocumentType(@PathVariable String type){
		return documentRepo.findByDocumentType(type);
	}
	
}
