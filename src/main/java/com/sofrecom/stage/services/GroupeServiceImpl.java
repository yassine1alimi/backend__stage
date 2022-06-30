package com.sofrecom.stage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.Groupe;
import com.sofrecom.stage.repository.GroupeRepository;





@Service
public class GroupeServiceImpl implements GroupeService {

	
	@Autowired
	GroupeRepository grouperepository;
	
	
	@Override
	public String addGroupe(Groupe groupe) {
		grouperepository.save(groupe);
		
      return "groupe added";
	}


	@Override
	public List<Groupe> getAllGroupes() {
		
		return (List<Groupe>) grouperepository.findAll();
	}

}
