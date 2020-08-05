package com.projeto.qi.projeto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.qi.projeto_final.model.Institution;
import com.projeto.qi.projeto_final.repository.InstitutionRepository;

@Service
public class InstitutionService {
	
	@Autowired
	private InstitutionRepository repository;

	public Institution save(Institution institution) {
		return repository.saveAndFlush(institution);
	}
	
	public List<Institution> getAll() {
		return repository.findAll();
	}
}
