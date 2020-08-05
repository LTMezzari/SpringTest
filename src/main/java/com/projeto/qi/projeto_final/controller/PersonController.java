package com.projeto.qi.projeto_final.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.qi.projeto_final.model.Person;
import com.projeto.qi.projeto_final.service.PersonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@PostMapping("/person")
	public Person save(@RequestBody @Valid Person person) {
		return service.save(person);
	}
}
