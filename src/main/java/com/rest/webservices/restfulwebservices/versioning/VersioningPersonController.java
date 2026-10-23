package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
		
	//1. URI Versioning
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Geetha Muniswamy");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Geetha", "Muniswamy"));
	}
	//http://localhost:8080/v2/person
	
	//2. Request Parameter Versioning
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Geetha Muniswamy");
	}
	//http://localhost:8080/person?version=1
	
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Geetha", "Muniswamy"));
	}
	//http://localhost:8080/person?version=2
	
	//3. Custom Header versioning
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Geetha Muniswamy");
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Geetha", "Muniswamy"));
	}
	
	// Test in talend api
	// Run by http://localhost:8080/person/header
	// Add X-API-VERSION : 1 as a header
	
	//4. Media type versioning (content Negotiation or accept header) - GitHub
	//URL produces=application/vnd.company.app-v1+json
	@GetMapping(path = "/person/accept", produces="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Geetha Muniswamy");
	}
	
	@GetMapping(path = "/person/accept", produces="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Geetha", "Muniswamy"));
	}
	
	// http://localhost:8080/person/accept
	// in Talend API
	// Add Accept: application/vnd.company.app-v1+json as headers
	
}
