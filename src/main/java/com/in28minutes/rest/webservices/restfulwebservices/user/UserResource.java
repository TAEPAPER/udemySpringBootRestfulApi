package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

/**
 * 상태 코드 정리
 * Resource is not found => 404
 * Server Exception => 500
 * Validation eror => 400
 * ===============================
 * 200 - Success
 * 201 - Created(Post 요청 시)
 * 204 - No Content
 * 401 - Anauthorized
 * 400 - Bad request (such as validation error[@Valid])
 * 404 - Resource not found
 * 500 - Server error 
 * 
 */
@RestController
public class UserResource {

	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	
	//http://localhost:8080/users
	
	//EntityModel
	//WebMvcLinkBuilder
	/**
	 * 
	 * Hateoas -> 사용자에게 더 많은 데이터를 제공할 수 있게 해줌
	 * EntityModel, WebMvcLinkBuilder 이용  
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		
		//EntityModel에 User 담아줌 
		EntityModel<User> entityModel = EntityModel.of(user);
		
		//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
		//WebMvcLinkBuilder의 linkTo : Creates a WebMvcLinkBuilder pointing to a controller method
		//this.getClass().retrieveAllUsers() : 현재 controller의 retrieveAllUsers() 메소드
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
		//링크 Rel 설정
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}

	/**
	 * ResponseEntity.created => 201 응답코드 반환
	 * location을 인자로 받음 (location은 response header에 추가)
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = service.save(user);
		
		//ServletUriComponentsBuilder.fromCurrentRequest() -> /users
		//.path("/{id}") -> /users/{id}
		//.buildAndExpand(savedUser.getId()) -> /users/4
		//.toUri() -> uri로 변환
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	
}
