package com.sofrecom.stage.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Groupe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	
	
	//@JsonManagedReference 
			//@JsonIgnore
			@ManyToMany
			private List<Employe> employes;
			@JsonIgnore
			@OneToMany(mappedBy="groupe")
			private List<ReclamationClient> reclamations;
			@ManyToOne
			private Departement departement;
}
