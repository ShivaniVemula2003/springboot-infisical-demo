package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {
	private final SecretService secretService;

	public SecretController(SecretService secretService) {
		this.secretService = secretService;
	}
	
	@Autowired
	Environment env;
	
	@GetMapping("/secrets")
	public  Map<String, String> getsecrets() {
		try {
			// If projectId/envSlug are configured in properties, you can inject them instead of hardcoding
			
//			return "UserName : "+secretService.getUser("username")+", "
//					+ "Password : "+ secretService.getPassword("password");
			return secretService.getAllSecrets();
		} catch (Exception e) {
//			return "Error fetching DB_USER: " + e.getMessage();
			System.out.println(e.getMessage());
			return new HashMap<String,String>();
		}
	}
	@GetMapping("/dbdetails")
	public String getdbdetails() {
		try {
			
			return "UserName : "+env.getProperty("db.username")+", "
			+ "Password : "+ env.getProperty("db.password")+","
			+ "Name : "+ env.getProperty("db.name");
		} catch (Exception e) {
//			return "Error fetching DB_USER: " + e.getMessage();
			return e.getMessage();
			
		}
	}
}
