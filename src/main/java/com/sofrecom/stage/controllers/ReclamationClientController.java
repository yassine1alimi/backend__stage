package com.sofrecom.stage.controllers;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sofrecom.stage.utils.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofrecom.stage.models.Demande;
import com.sofrecom.stage.models.Employe;
import com.sofrecom.stage.models.ReclamationClient;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IEmployeRepo;
import com.sofrecom.stage.repository.IReclamationClientRepo;
import com.sofrecom.stage.repository.IUtilidateurRepo;
import com.sofrecom.stage.services.ReclamationClientService;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReclamationClientController {
	@Autowired
	private IReclamationClientRepo reclamationClientRepo;
	@Autowired
	private IUtilidateurRepo userRepo ;
	private static final Logger logger = LoggerFactory.getLogger(ReclamationClientController.class);
@Autowired
private ServiceManager serviceManager;
    @Autowired
    private ReclamationClientService reclamationClientService;
@Autowired
private IEmployeRepo employeRepo;
	@PostMapping("/create")
    public ReclamationClient createCandidate(@Valid @RequestBody ReclamationClient reclamationClient) {
		return reclamationClientRepo.save(reclamationClient);
		
	}
	
	/*
	@PostMapping("/createReclamation")
	public ReclamationClient createReclamation ( @Valid @RequestBody ReclamationClient reclamationClient, @RequestParam Long id ) {
		
		UserInformation user = userRepo.findById(id).get();
		reclamationClient.setUserReclamation(user);
		return reclamationClientRepo.save(reclamationClient);
	}
	
	
	*/
	
	
	@GetMapping("/ReclamationClients")
	public List<ReclamationClient> getAllReclamationClients() {
		return reclamationClientRepo.findAll();
	}

	
	private final Path rootLocation = Paths.get("C:\\Users\\STRIX\\Downloads1");	
	
	@PostMapping("/createreclamationClient1")
    public ResponseEntity<?> createReclamationClient(@RequestPart("reclamationClient") String reclamationClient, @RequestParam("pj1") 
    MultipartFile file1,@RequestParam("pj2") MultipartFile file2,@RequestParam Long id) throws JsonParseException, JsonMappingException, IOException {


		this.serviceManager.restTemplateStart("http://localhost:9090/process/start");
		ReclamationClient reclamationClient1 = new ObjectMapper().readValue(reclamationClient , ReclamationClient.class);
				System.out.println(reclamationClient1);
				UserInformation user = userRepo.findById(id).get();

				reclamationClient1.setPj1(file1.getOriginalFilename());
				
				reclamationClient1.setPj2(file2.getOriginalFilename());
				reclamationClient1.setUserReclamation(user);

				ReclamationClient reclamationClient2 = reclamationClientRepo.save(reclamationClient1);
				if (reclamationClient2!=null) {
					this.serviceManager.restTemplateAssignetask("http://localhost:9090/process/assignetask",reclamationClient1.getEmail());

					this.serviceManager.restTemplateCompleteTask("http://localhost:9090/process/completetask");
					String application_status = this.serviceManager.restTemplategetstatus("http://localhost:9090/process/getoutputVariables");

					return  ResponseEntity.status(HttpStatus.ACCEPTED).body("reclamation is saved");
				}else {
					return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("reclamation is not saved");
			}	
	}
	
	
	@PostMapping("/createreclamationClient")
    public ResponseEntity<?> createReclamationClient(@RequestPart("reclamationClient") String reclamationClient, @RequestParam("pj1") 
    MultipartFile file1,@RequestParam("pj2") MultipartFile file2) throws JsonParseException, JsonMappingException, IOException {
		
		
			
		ReclamationClient reclamationClient1 = new ObjectMapper().readValue(reclamationClient , ReclamationClient.class);
				System.out.println(reclamationClient1);
				
				reclamationClient1.setPj1(file1.getOriginalFilename());
				
				reclamationClient1.setPj2(file2.getOriginalFilename());
				ReclamationClient reclamationClient2 = reclamationClientRepo.save(reclamationClient1);
				if (reclamationClient2!=null) {
				return  ResponseEntity.status(HttpStatus.ACCEPTED).body("User is saved");
				}else {
					return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not saved");
			}	
	}
	
	 
	
	@GetMapping("/downloadFile/{fileName:.+}")
	
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
		
        // Load file as Resource
        Resource resource = reclamationClientService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
	@GetMapping("/getreclamationClient/{id}")
	public ResponseEntity<ReclamationClient> findById(@PathVariable("id") Long id) {
		Optional<ReclamationClient> emp = reclamationClientRepo.findById(id);
		if (emp.isPresent())
			return new ResponseEntity<ReclamationClient>(emp.get(), HttpStatus.OK);
		else
			return new ResponseEntity<ReclamationClient>(HttpStatus.NO_CONTENT);

	}
	
	 @DeleteMapping("/reclamationClient/{id}")
		void deleteNote(@PathVariable Long id) {
		 reclamationClientRepo.deleteById(id);
		  }
		  
	 
	 @GetMapping("/accepterReclamation")
		public void accepterReclamation( @RequestParam Long id) {
		
		 reclamationClientService.accepterReclamationClient(id);
			
	}
	
	 @GetMapping("/refuserReclamation")
		public void refuserReclamation (@RequestParam Long id) {
			
		 reclamationClientService.refuserReclamationClient(id);
		}
		 
	 @GetMapping("reclamationClients_attente")
		public List<ReclamationClient> getReclamationClientByStatus (){
			return reclamationClientRepo.getReclamationClientByStatus();
		}
	 
	 
	 @GetMapping("/reclamationClientUser/{id}")
		public List<ReclamationClient> getReclamationClientsByIdUser(@PathVariable("id") Long id){
			
			UserInformation user = userRepo.findById(id).get();
			return user.getReclamations();
			
		}
	 @DeleteMapping("/deleteReclamationClient/{id}")
		public ResponseEntity<ReclamationClient> deleteReclamationClient (@PathVariable("id") Long id) {
			Optional<ReclamationClient> reclamationClient = reclamationClientRepo.findById(id);
			if (reclamationClient.isPresent()) {
				reclamationClientRepo.delete(reclamationClient.get());
				return new ResponseEntity<ReclamationClient>(reclamationClient.get(), HttpStatus.ACCEPTED);
			} else
				return new ResponseEntity<ReclamationClient>(HttpStatus.NOT_ACCEPTABLE);
		}
	 
	 
		 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @GetMapping("/reclamations/statnombre")
		public Long getnombrerecalamation() {
		Long sum = reclamationClientService.Number_reclamation();
		return sum;
				
		
	}
		@GetMapping("/reclamations/{etat}/statetat")
		public Long getetatrecalamation(@PathVariable String etat) {
		Long sum = reclamationClientService.Etat_reclamation(etat);
		return sum;
		
	}
	 
		@GetMapping("/reclamations/{type}/statetat")
		public Long gettyperecalamation(@PathVariable String typeClaim) {
		Long sum = reclamationClientService.Type_reclamation(typeClaim);
		return sum;
		
	}
	 
		@GetMapping("/reclamations/{departement}/statetat")
		public Long getdepartementrecalamation(@PathVariable String departement) {
		Long sum = reclamationClientService.Departement_reclamation(departement);
		return sum;
		
	}
	 
}
        
	
	

