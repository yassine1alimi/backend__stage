package com.sofrecom.stage.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.sofrecom.stage.models.Claim;
import com.sofrecom.stage.models.Demande;
import com.sofrecom.stage.models.UserInformation;
import com.sofrecom.stage.repository.IReclamationClientRepo;
import com.sofrecom.stage.repository.IClaimRepo;
import com.sofrecom.stage.repository.IDemandeRepo;
import com.sofrecom.stage.repository.IUtilidateurRepo;
import com.sofrecom.stage.services.ReclamationClientService;
import com.sofrecom.stage.services.EmployeServiceImpl;

@CrossOrigin
@RestController

public class ClaimController {
/*	@Autowired
	private IReclamationClientRepo candidatRepo;
	
	@Autowired
	private ClaimServiceImpl claimService ;
	
	@Autowired
	private IUtilidateurRepo userRepo ;
	
	
	
	@Autowired
	private IClaimRepo claimRepo ; 
	
	private EmployeServiceImpl employeService;
	
	 @Autowired
	    private ReclamationClientService candidatService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	@GetMapping("/claims")
	public List<Claim> getAllClmaim(){
		return claimRepo.findAll();
				
	}
	
	
	@PostMapping("/createClaim")
	public Claim createClaim ( @Valid @RequestBody Claim claim, @RequestParam Long id ) {
		
		UserInformation user = userRepo.findById(id).get();
		claim.setUser1(user);
		return claimRepo.save(claim);
	}
	
	@PostMapping("/createclaim")
    public ResponseEntity<?> createCandidate(@RequestPart("claim") String claim, @RequestParam("claim_file") 
    MultipartFile file1) throws JsonParseException, JsonMappingException, IOException {
		
		
			
				Claim claim1 = new ObjectMapper().readValue(claim , Claim.class);
				System.out.println(claim1);
				claim1.setFileClaim(file1.getOriginalFilename());
				//claim1.setPathCv(file1.getOriginalFilename());
				//claim1.setPathMotivationLetter(file2.getOriginalFilename());
				Claim claim2 = claimRepo.save(claim1);
				if (claim2!=null) {
				return  ResponseEntity.status(HttpStatus.ACCEPTED).body("User is saved");
				}else {
					return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not saved");
			}	
	}
	
	@GetMapping("/downloadFileClaim/{fileName:.+}")
	
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
		
        // Load file as Resource
        Resource resource = candidatService.loadFileAsResource(fileName);

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
	
	
	
	
	
	@GetMapping("/claim/{id}")
	public ResponseEntity<Claim> findById(@PathVariable("id") Long id) {
		Optional<Claim> claim = claimRepo.findById(id);
		if (claim.isPresent())
			return new ResponseEntity<Claim>(claim.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Claim>(HttpStatus.NO_CONTENT);

	}
	
	
	
	@GetMapping("/claimUser/{id}")
	public List<Claim> getClaimByIdUser(@PathVariable("id") Long id){
		
		UserInformation user = userRepo.findById(id).get();
		return user.getClaims();
		
	}
	
	@GetMapping("/accepterClaim")
	public void accepterClaim( @RequestParam Long id) {
	
      claimService.accepterClaim(id);
		
}
	
	
	@GetMapping("/refuserClaim")
	public void refuserClaim (@RequestParam Long id) {
		
		claimService.refuserClaim(id);
	}
	
	
	
	
	
	
	@DeleteMapping("/deleteClaim/{id}")
	public ResponseEntity<Claim> deleteClaim (@PathVariable("id") Long id) {
		Optional<Claim> claim = claimRepo.findById(id);
		if (claim.isPresent()) {
			claimRepo.delete(claim.get());
			return new ResponseEntity<Claim>(claim.get(), HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<Claim>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@GetMapping("claims_attente")
	public List<Claim> getClaimByStatus (){
		return claimRepo.getClaimByStatus();
	}
	
	
	
	
	
	*/
	
	
	
	
}
