package com.sofrecom.stage.services;



import java.util.Date;
import java.util.List;

import com.sofrecom.stage.models.Employe;
import com.sofrecom.stage.models.ReclamationClient;
public interface ITimesheetService {
	public Long ajouterReclamation(ReclamationClient reclamation);
	public ReclamationClient getReclamationById(Long idReclamation);
	public int affecterReclamationAGroupe(Long idReclamation, int groupeId);
	public void ajouterTimesheet(Long idReclamation, Long idUser, Date dateDebut, Date dateFin);
	public int validerTimesheet(Long idReclamation, Long idUser, Date dateDebut, Date dateFin, Long validateurId);
	public List<ReclamationClient> findAllReclamationByEmployeJPQL(Long idUser);
	public List<Employe> getAllEmployeByReclamation(Long reclamationId);


}
