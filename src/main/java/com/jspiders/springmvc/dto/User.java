package com.jspiders.springmvc.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	@Column(unique = true,nullable = false)
	private String username;
	private String password;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private long mobile;
	private String securtiyQue;
	private String answer;

}
