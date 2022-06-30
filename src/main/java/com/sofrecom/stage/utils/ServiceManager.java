package com.sofrecom.stage.utils;

import java.util.Collections;

import org.apache.catalina.users.GenericUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceManager {

	public void restTemplateAssignetask(String resourceURL,String userid) {
		RestTemplate restTemplate = new RestTemplate();
		String requestJson = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Content-Type", "application/json");
		requestJson = userid.toString();
		
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.POST,entity,String.class);
		System.out.println(response);
	}
	
	public void restTemplateCompleteTask(String resourceURL) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Content-Type", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.POST,entity,String.class);
		System.out.println(response);
	}

	public void restTemplateStart(String resourceURL) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Content-Type", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.POST,entity,String.class);
		System.out.println(response);
	}

	
	
	
	public String restTemplategetstatus(String resourceURL) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Content-Type", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.GET,entity,String.class);
		System.out.println(response);
		return response.getBody();
	}

	public void restTemplateCreateUser(String resourceURL,GenericUser user) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Content-Type", "application/json");
		
		HttpEntity<Object> entity = new HttpEntity<Object>(user,headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.POST,entity,String.class);
		System.out.println(response);
	}


}
