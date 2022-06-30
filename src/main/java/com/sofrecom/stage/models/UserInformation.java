package com.sofrecom.stage.models;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(	name = "users", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "username"),
	@UniqueConstraint(columnNames = "email") 
})
public class UserInformation implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	
	
	private String username;

	private String prenom;
	
	private String nom;
	
	private LocalDate dateOfBirth;
	
	private String phone;
	
	private String email;
	private String cin;
	private String adresse ; 
	private String ville; 
	private String nationality; 
	private String family_status; 
	private String photo ="avatar.jpg" ; 
	
	private String password;
	private String repassword;
	private String fonction;

	//private String coutHeuresSup;
	//private int dureeConges = 30;
	//private int joursConges;
	//private int soldeConges = 30;
	//private String id_card_number;
	
	//private int budget = 0;
	private int archived = 1 ; 
	
	
	//private boolean archived;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Demande> demandes;
	/*@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
	private List<Demande> claims;

	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userReclamation")
	private List<ReclamationClient> Reclamations;
	
	

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	public UserInformation (String username, String email,LocalDate dateOfBirth,String phone, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
	}


	public UserInformation(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
/*
	@JsonIgnore
	//@JsonBackReference
	@OneToMany(mappedBy="user2")
	private List<Timesheet> timesheets;
	
	*/
	
	
	
	@OneToMany(mappedBy = "postCreator",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Post> userPosts;


    @OneToMany(mappedBy = "postEvaluator",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<EvaluatePost> evaluatePostSet;



	@ElementCollection
	Set<String> userPreferences;
}
