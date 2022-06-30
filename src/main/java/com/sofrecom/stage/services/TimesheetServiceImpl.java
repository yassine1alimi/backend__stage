package com.sofrecom.stage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.ERole;
import com.sofrecom.stage.models.Employe;
import com.sofrecom.stage.models.Groupe;
import com.sofrecom.stage.models.ReclamationClient;
import com.sofrecom.stage.models.Role;
import com.sofrecom.stage.models.Timesheet;
import com.sofrecom.stage.models.TimesheetPK;
import com.sofrecom.stage.repository.GroupeRepository;
import com.sofrecom.stage.repository.IEmployeRepo;
import com.sofrecom.stage.repository.IReclamationClientRepo;
import com.sofrecom.stage.repository.TimesheetRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;


@Service
public class TimesheetServiceImpl implements ITimesheetService{
	public static final Logger l = Logger.getLogger(DepartementServiceImpl.class);

	
	@Autowired
	IReclamationClientRepo reclamationRepository;
	@Autowired
	GroupeRepository groupeRepository;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	IEmployeRepo userRepository;
	
	
	
	
	@Override
	public Long ajouterReclamation(ReclamationClient reclamation) {
		l.info("START ajoutReclamation ");
		try {
			//l.debug(reclamation.getId());
			l.debug(reclamation.getEmploye());

			
			l.trace("debut d'ajout du reclamation :" + reclamation.getTypeClaim());
			reclamationRepository.save(reclamation);
			l.trace("fin ajout");
			
			l.debug("reclamation " + reclamation.getTypeClaim() + " de l'id: " + reclamation.getIdReclamation() + " ajoutée avec succée");
		} catch(Exception e) {
		  l.error("Error : " + e);
		}

		return reclamation.getIdReclamation();

	}




	@Override
	public ReclamationClient getReclamationById(Long reclamationId) {
		l.info("Start get reclamation by Id");
		try {
			l.trace("debut getreclamationById");
			Optional<ReclamationClient> m = reclamationRepository.findById(reclamationId);
			l.trace("fin getReclamationById");
		return m.isPresent() ? m.get() : null;				
		} catch(Exception e) {
			l.error("Error : " + e);
		}
		
		return null;
	}




	@Override
	public int affecterReclamationAGroupe(Long reclamationId, int groupeId) {
		l.info("Start affect reclamation to groupe");
		try {
			l.trace("debut affecterReclamationAFroupe");
			Optional<ReclamationClient> r = reclamationRepository.findById(reclamationId);
			Optional<Groupe> g = groupeRepository.findById(groupeId);
			Groupe groupe = new Groupe();
			ReclamationClient reclamation = new ReclamationClient();
			if (r.isPresent()){
				reclamation = r.get();
			}
			if (g.isPresent())
			{
				groupe = g.get();
			}	
			reclamation.setGroupe(groupe);
			reclamationRepository.save(reclamation);
			if (l.isDebugEnabled())
			{
				l.debug(String.format("reclamation %s ajouté au groupe %s", reclamation.getTypeClaim(), groupe.getName()));
			}
				
			l.trace("fin affecterReclamationAFroupe");
			return reclamation.getGroupe().getId();
		}
		catch(Exception e) {
			l.error("Error : " + e);
		}
		return -1;

	}




	@Override
	public void ajouterTimesheet(Long reclamationId, Long userId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdUser(userId);
		timesheetPK.setIdReclamation(reclamationId);
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); // par defaut non valide
		timesheetRepository.save(timesheet);

		l.debug("timesheet ajouté!");

	}


	
	
	


	@Override
	public int validerTimesheet(Long reclamationId, Long userId, Date dateDebut, Date dateFin, Long validateurId) {
		Optional<Employe> validateur = userRepository.findById(validateurId);
		Optional<ReclamationClient> reclamation = reclamationRepository.findById(reclamationId);

		Employe e = new Employe();
		ReclamationClient m = new ReclamationClient();

		if (validateur.isPresent()){
			e = validateur.get();
		}

		if(reclamation.isPresent())
		{
			 m = reclamation.get();
		}
		// verifier s'il est un chef de groupe (interet des enum)
		if (!e.getRoles().equals(ERole.CHEF_GROUPE)) {
			if (l.isDebugEnabled())
			{
				l.debug("l'employe doit etre chef de groupe pour valider une feuille de temps !");
			}

			return -1;
		}
		// verifier s'il est le chef de groupe de la reclamation en question
		boolean chefDeLaReclamation = false;
		for (Groupe groupe : e.getGroupes()) {
			if (groupe.getId() == m.getGroupe().getId()) {
				chefDeLaReclamation = true;
				break;
			}
		}
		if (!chefDeLaReclamation) {
			if (l.isDebugEnabled()){
				l.debug("l'employe doit etre chef de groupe de la reclamation en question");
			}
		
			return 0;
		}
		//
		TimesheetPK timesheetPK = new TimesheetPK(reclamationId, userId, dateDebut, dateFin);
		Timesheet timesheet = timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		l.debug("timesheet validé!");

		// Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if (l.isDebugEnabled())
		{
			l.debug(String.format("dateDebut : %s",dateFormat.format(timesheet.getTimesheetPK().getDateDebut())));
		}
		


		return 1;

	}




	@Override
	public List<ReclamationClient> findAllReclamationByEmployeJPQL(Long employeId) {
		return	timesheetRepository.findAllReclamationByUserJPQL(employeId);
	}




	@Override
	public List<Employe> getAllEmployeByReclamation(Long reclamationId) {
		
		return timesheetRepository.getAllUserByReclamation(reclamationId);
		
	}

	

}
