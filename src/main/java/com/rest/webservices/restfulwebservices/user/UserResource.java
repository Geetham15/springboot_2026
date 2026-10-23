package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}

	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();

	}
	// Output: localhost:8080/users
	// http://localhost:8080/users

	/*
	 * [ { "id": 1, "name": "Adam", "birthDate": "1996-10-15" }, { "id": 2, "name":
	 * "Eve", "birthDate": "2001-10-15" }, { "id": 3, "name": "Jim", "birthDate":
	 * "2006-10-15" } ]
	 */

	// GET user by id
	
	//http://localhost:8080/users
	
	//EntityModel
	//WebMvcLinkBuilder
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id:" +id);
		
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	// Run by command localhost:8080/users/1
	// { "id": 1, "name": "Adam", "birthDate": "1996-10-15" }

	// DELETE user by id
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);

	}
		
	// POST /users
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();// /users/{id}, user.getID()
		//Return location.
		return ResponseEntity.created(location).build();
		
		// Return the HTTP status code --> 201- Created Ok
		// return ResponseEntity.created(null).build();
		
	}
	// user Talend API Tester to invoke, discover and test HTTP and REST APIs.
}
