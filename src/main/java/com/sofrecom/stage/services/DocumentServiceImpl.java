package com.sofrecom.stage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.Document;
import com.sofrecom.stage.repository.IDocumentRepo;



@Service
public class DocumentServiceImpl {

	@Autowired
	private IDocumentRepo docRepo;

	public List<Document> getAllDocuments(Document doc) {
		return docRepo.findAll();
	}
	
	public Document saveDocument (Document doc) {
		return docRepo.save(doc);
	}
	

	
}
