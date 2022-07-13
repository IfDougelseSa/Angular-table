package com.ibm.angularTable.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.angularTable.dto.UserDTO;
import com.ibm.angularTable.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource implements Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> list = service.getAllUsers();
		return ResponseEntity.ok().body(list);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		UserDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
}
