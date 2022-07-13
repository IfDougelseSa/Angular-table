package com.ibm.angularTable.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.angularTable.dto.UserDTO;
import com.ibm.angularTable.entities.User;
import com.ibm.angularTable.repositories.UserRepository;
import com.ibm.angularTable.services.exceptions.DatabaseException;
import com.ibm.angularTable.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public List<UserDTO> getAllUsers() {
		List<User> list = repository.findAll();
		return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
			User entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setAge(dto.getAge());
			entity = repository.save(entity);
			return new UserDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e){
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation " + id);
		}
		
	}
	
	
}
