package com.projeto.qi.projeto_final.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.qi.projeto_final.model.Institution;
import com.projeto.qi.projeto_final.model.Person;
import com.projeto.qi.projeto_final.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	public Person save(Person person) {
		return repository.saveAndFlush(person);
	}
	
	public List<Person> getByInstitution(Institution institution) {
		ArrayList<Person> persons = new ArrayList<>();
		for (Person p : repository.findAll()) {
			if (institution.getId() == p.getInstitution().getId()) {
				persons.add(p);
			}
		}
		return persons;
	}
}
