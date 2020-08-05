package com.projeto.qi.projeto_final.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.qi.projeto_final.model.Institution;
import com.projeto.qi.projeto_final.service.InstitutionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InstitutionController {

	@Autowired
	private InstitutionService service;
	
	@PostMapping("/institution")
	public Institution save(@RequestBody @Valid Institution institution) {
		return service.save(institution);
	}
	
	@GetMapping("/institution")
	public List<Institution> getInstitutions() {
		return service.getAll();
	}
}
