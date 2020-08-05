package com.projeto.qi.projeto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.qi.projeto_final.model.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
