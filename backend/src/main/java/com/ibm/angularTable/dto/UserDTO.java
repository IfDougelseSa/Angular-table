package com.ibm.angularTable.dto;

import java.io.Serializable;

import com.ibm.angularTable.entities.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer age;

	public UserDTO() {

	}

	public UserDTO(Long id, String name, Integer age) {

		this.id = id;
		this.name = name;
		this.age = age;
	}

	public UserDTO(User entity) {

		this.id = entity.getId();
		this.name = entity.getName();
		this.age = entity.getAge();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
