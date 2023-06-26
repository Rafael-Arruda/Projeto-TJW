package com.tjw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.entities.Table_professores;

public interface ProfessorRepository extends JpaRepository<Table_professores, Long> {
	List<Table_professores> findByEmailContaining(String email);
}
