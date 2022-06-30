package com.sofrecom.stage.controllers;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Employe;
import com.sofrecom.stage.models.ReclamationClient;
import com.sofrecom.stage.services.EmployeServiceImpl;
import com.sofrecom.stage.services.ITimesheetService;






@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/timesheet")

public class TimesheetController {
	@Autowired
	EmployeServiceImpl usereservice;
	
	@Autowired
	ITimesheetService itimesheetservice;
	
	// http://localhost:8087/ajouterReclamation
	//{"id":4,"name":"mareclamation", "description":"c ma reclamation"}
	@PostMapping("/ajouterReclamationClient")
	@ResponseBody
	public Long ajouterReclamationClient(@RequestBody ReclamationClient reclamation) {
		itimesheetservice.ajouterReclamation(reclamation);
		return reclamation.getIdReclamation();
	}
	
	
	
	
	
	// http://localhost:8081/SpringMVC/servlet/affecterReclamationAGroupe/4/4
		@PutMapping(value = "/affecterReclamationAGroupe/{idreclamation}/{idgroupe}") 
		public void affecterReclamationAGroupe(@PathVariable("idreclamation") Long reclamationId, @PathVariable("idgroupe") int groupeId) {
			itimesheetservice.affecterReclamationAGroupe(reclamationId, groupeId);
		}
		
		
		
		
		
		// http://localhost:8081/SpringMVC/servlet/ajouterTimesheet
	    //{"reclamationId":1,"userId":2,"dateDebut":"2020-03-01","dateFin":"2021-03-01"}
		
		@PostMapping("/ajouterTimesheet/{idreclamation}/{iduser}/{dated}/{datef}")
		@ResponseBody
		public void ajouterTimesheet(@PathVariable("idreclamation") Long reclamationId, @PathVariable("iduser") Long userId, @PathVariable("dated") Date dateDebut,@PathVariable("datef") Date dateFin) {
			itimesheetservice.ajouterTimesheet(reclamationId, userId, dateDebut, dateFin);
		}
		

		
		
		
		
		
		
		
		// http://localhost:8087/SpringMVC/servlet/validerTimesheet/1/1/03-10-2020/03-20-2020/1
		@PutMapping(value = "/validerTimesheet/{idreclamation}/{iduser}/{dated}/{datef}/{idval}") 
		public void validerTimesheet(@PathVariable("idreclamation") Long reclamationId, @PathVariable("iduser") Long userId, @PathVariable("dated") Date dateDebut,@PathVariable("datef") Date dateFin, @PathVariable("idval") Long validateurId) {
			itimesheetservice.validerTimesheet(reclamationId, userId, dateDebut, dateFin, validateurId);
		}
		
		
		
		
		
		
		
		// URL : http://localhost:8087/SpringMVC/servlet/findAllReclamationByEmployeJPQL/1
	    @GetMapping(value = "findAllReclamtionByEmployeJPQL/{iduser}")
	    @ResponseBody
		public List<ReclamationClient> findAllReclamationByEmployeJPQL(@PathVariable("iduser") Long userId) {

			return itimesheetservice.findAllReclamationByEmployeJPQL(userId);
		}

		
		
	    
	    
	    
	 // URL : http://localhost:8087/SpringMVC/servlet/getAllEmployeByReclamation/1
	    @GetMapping(value = "getAllEmployeByReclamtion/{idreclamation}")
	    @ResponseBody
		public List<Employe> getAllEmployeByReclamtion(@PathVariable("idreclamation") Long reclamationId) {

			return itimesheetservice.getAllEmployeByReclamation(reclamationId);
		}
	    
	    
		
		
}
