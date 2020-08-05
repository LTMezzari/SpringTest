package com.projeto.qi.projeto_final.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity(name="tb_institution")
public class Institution implements Serializable{

	private static final long serialVersionUID = 295836093093437013L;
	
	@Id
	@GeneratedValue
	@Column(name="pk_insitution")
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message="O nome não pode estar vazio")
	private String name;

	@Column(nullable = false)
	@NotBlank(message="O endereço não pode estar vazio")
	private String address;

	@Column(nullable = false)
	@NotBlank(message="O e-mail não pode estar vazio")
	private String email;

	@Column(nullable = false)
	@NotBlank(message="O telefone não pode estar vazio")
	private String phone;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
