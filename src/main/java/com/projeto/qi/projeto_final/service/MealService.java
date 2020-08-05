package com.projeto.qi.projeto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.qi.projeto_final.model.Meal;
import com.projeto.qi.projeto_final.model.MealImage;
import com.projeto.qi.projeto_final.repository.MealImageRepository;
import com.projeto.qi.projeto_final.repository.MealRepository;

@Service
public class MealService {

	@Autowired
	private MealRepository repository;

	@Autowired
	private MealImageRepository imageRepository;
	
	public Meal save(Meal meal) {
		return repository.saveAndFlush(meal);
	}
	
	public MealImage save(MealImage mealImage) {
		return imageRepository.saveAndFlush(mealImage);
	}
	
	public List<MealImage> getAll() {
		return imageRepository.findAll();
	}
}
