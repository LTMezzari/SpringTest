package com.projeto.qi.projeto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.qi.projeto_final.model.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

}
