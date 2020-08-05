package com.projeto.qi.projeto_final.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name="tb_person")
public class Person implements Serializable{

	private static final long serialVersionUID = -4644880603001247749L;

	@Id
	@GeneratedValue
	@Column(name="pk_person")
	private Long id;
	
	@Column(nullable=false)
	@NotBlank(message="O nome não deveria estar vazio")
	private String name;
	
	@Column(nullable=false)
	@NotBlank(message="O e-mail não deveria estar vazio")
	private String email;
	
	@Column(nullable=false)
	@NotBlank(message="O telefone não deveria estar vazio")
	private String phone;
	
	@ManyToOne
	@NotNull(message="É necessário vincular um instituição")
	private Institution institution;
	
	@Column(nullable=false)
	private boolean wantToReceiveMail = false;

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

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public boolean wantToReceiveMail() {
		return wantToReceiveMail;
	}

	public void setWantToReceiveMail(boolean wantToReceiveMail) {
		this.wantToReceiveMail = wantToReceiveMail;
	}
}
