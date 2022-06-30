package com.sofrecom.stage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Groupe;
import com.sofrecom.stage.services.GroupeService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GroupeController {
	
	
	
	@Autowired
	GroupeService groupeservice;
	
	
	@PostMapping("/ajouterGroupe111")
	@ResponseBody
	public Groupe ajouterGroupe(@RequestBody Groupe groupe)
	{
		groupeservice.addGroupe(groupe);
		return groupe;
	}
	

}
