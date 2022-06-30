package com.sofrecom.stage.services;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.sofrecom.stage.models.Departement;
import com.sofrecom.stage.models.Groupe;
import com.sofrecom.stage.repository.DepartementRepository;
import com.sofrecom.stage.repository.GroupeRepository;
@Service

public class DepartementServiceImpl implements IDepartementService{

	// public static final Logger logger = Logger.getLogger(DepartementServiceImpl.class);

	@Autowired
	DepartementRepository departementRepoistory;
	@Autowired
	GroupeRepository groupeRepoistory;
	
	
	
	@Override
	public int ajouterDepartement(Departement departement) {
		//logger.info("START ajouterdepartement ");

		try {
			// logger.debug(groupe.getId());

			// logger.trace("debut d'ajout du groupe: " + groupe.getName());
			departementRepoistory.save(departement);
			// logger.trace("fin Ajout");

			// logger.debug("le groupe: " + groupe.getName() + " de l'id: " + groupe.getId() + " ajoutée avec succé");

		} catch (Exception e) {
		//	logger.error("Error :"  + e);
		}
		// logger.info("END ajouterde groupe ");

		return departement.getId();
	}

	
	
	
	


	@Override
	public int ajouterGroupe(Groupe groupe) {
		//logger.info("START ajouterdepartement ");

		try {
			// logger.debug(groupe.getId());

			// logger.trace("debut d'ajout du groupe: " + groupe.getName());
			groupeRepoistory.save(groupe);
			// logger.trace("fin Ajout");

			// logger.debug("le groupe: " + groupe.getName() + " de l'id: " + groupe.getId() + " ajoutée avec succé");

		} catch (Exception e) {
		//	logger.error("Error :"  + e);
		}
		// logger.info("END ajouterde groupe ");

		return groupe.getId();
	}


	
	
	
	
	
	

	@Override
	public int affecterGroupeADepartement(int groupeId, int departementId) {
		// logger.info("START affecterGroupeADepartement ");
		Groupe groupe = new Groupe();
		try {
		//	logger.debug("init groupe" + groupe);

			Optional<Departement> e = departementRepoistory.findById(departementId);
			Optional<Groupe> d = groupeRepoistory.findById(groupeId);
			// logger.trace("Début Test : verifier l'existence du l'departement et du Groupe");

			if (e.isPresent() && d.isPresent()) {

				// logger.trace("début: récuperer departement by ID");
				Departement departementManagedEntity = e.get();
				// logger.trace("fin: récuperer Departement by ID");

				// logger.trace("début: récuperer groupe by ID");
				Groupe groupeManagedEntity = d.get();
				// logger.trace("début: récuperer groupe by ID");

				// logger.trace("début: affectation d'Departement -> groupe");
				groupeManagedEntity.setDepartement(departementManagedEntity);
				groupeRepoistory.save(groupeManagedEntity);
			//	logger.trace("fin: affectation de departement -> groupe");

				groupe = groupeManagedEntity;

				// logger.debug("new groupe" + groupe);

				// logger.debug("departement: " + groupeManagedEntity.getDepartement().getId() + "-"
				//		+ groupeManagedEntity.getDepartement().getName() + "affecté au groupe:"
				//		+ groupeManagedEntity.getName() + "-" + groupeManagedEntity.getId());
				// logger.trace("FIN Test : verifier l'existence du l'departement et du groupe");

			}
		 else {
			//logger.trace("departement ou groupe n'exitse pas");
			// logger.trace("FIN Test : verifier l'existence du l'departeùent et du groupe");
		}

		} catch (Exception e) {
			// logger.error("Error :"  + e);
		}

	//	logger.info("END affecterGroupetADepartement ");
		return groupe.getDepartement().getId();
	}







	@Override
	public List<String> getAllGroupesNamesByDepartement(int departementId) {
		// logger.info("START getAllGroupesNamesByDepartement ");
		List<String> groupeNames = new ArrayList<>();
		Optional<Departement> e = departementRepoistory.findById(departementId);
	
		for(String elem: groupeNames)
	       {
			//logger.debug("Init List"+elem);
	       }
		
		//logger.trace("Début Test : verifier l'existence du departement");

		if (e.isPresent()) {
			//logger.trace("Début Get : departement");
			Departement departementManagedEntity = e.get();
			// logger.trace("FIN Get : Departement");

			// logger.trace("Début parcour : de liste des groupe de departement");
			for (Groupe groupe : departementManagedEntity.getGroupes()) {
				groupeNames.add(groupe.getName());
			}
			// logger.trace("FIN parcour de liste des groupes de departement");

			
			for(String elem: groupeNames)
		       {
				// logger.debug("Final List"+elem);
		       }

			return groupeNames;
		}
		
		// logger.trace("FIN Test : verifier l'existence du departement");

		
		
		
		// logger.info("END getAllGroupesNamesByDepartement ");

		return groupeNames;

	}







	@Override
	public Departement getDepartementById(int departementId) {
		
			try {
				Optional<Departement> e = departementRepoistory.findById(departementId);
				if (e.isPresent()) {

					return e.get();
				}
			} catch (Exception e) {

			}

			return null;
		}







	@Override
	public int deleteDepartementById(int departementId) {
		//logger.info("START deleteDepartementById ");
		Optional<Departement> e = departementRepoistory.findById(departementId);

		try {

			//logger.trace("Début Test : verifier l'existence du departeùment");
			if (e.isPresent()) {
				
				//logger.debug("depart exitse:" + e.get().getId());

				//logger.trace("débbut suppression");
				departementRepoistory.delete(e.get());
				//logger.trace("fin suppression");
				//logger.trace("FIN Test : verifier l'existence du departement");
				
				return 1;
			} else {
				//logger.trace("depart n'exitse pas");
				//logger.trace("FIN Test : verifier l'existence du departement");
				return -1;
			}

		} catch (Exception err) {
			//logger.error("Error :"  + err);

		}
		if(e.isPresent()) {
			//logger.debug("departement supprimée:" + e.get().getId());
		}
	
		//logger.info("END deletedepartementId ");

		return 0;

	}







	@Override
	public int deleteGroupeById(int groupeId) {
		//logger.info("START deleteGroupeById ");
		Optional<Groupe> d = groupeRepoistory.findById(groupeId);

		try {
			//logger.trace("Début Test : verifier l'existence du lGroupe");

			if (d.isPresent()) {
				//logger.debug("groupe exitse:" + d.get().getId());

				//logger.trace("débbut suppression");
				groupeRepoistory.delete(d.get());
				//logger.trace("fin suppression");
				//logger.trace("FIN Test : verifier l'existence du groupe");
				return 1;
			} else {

				//logger.trace("groupe n'exitse pas");
				//logger.trace("FIN Test : verifier l'existence du groupe");
				return -1;
			}

		} catch (Exception e) {
			//logger.error("error:"+ e);

		}
		if(d.isPresent()) {
			//logger.debug("Departement suprimée:" + d.get().getId());
		}
		//logger.info("END deleteGroupeById ");
		return 0;

	}







	@Override
	public Groupe getGroupeById(int groupeId) {
	//	logger.info("START getGroupeById ");
		try {
			Optional<Groupe> d = groupeRepoistory.findById(groupeId);

			//logger.trace("Début Test : verifier l'existence du groupe");
			if (d.isPresent()) {

				//logger.debug("groupe exitse:" + d.get().getId());

				//logger.trace("début Get");
				return d.get();
			}
			//logger.trace("FIN Test : verifier l'existence du groupe");
		} catch (Exception e) {
			//logger.error("error:"+ e);

		}
		//logger.info("END getGroupeById ");

		return null;
	}
	}
	
	
	
	
	
	
	
	

