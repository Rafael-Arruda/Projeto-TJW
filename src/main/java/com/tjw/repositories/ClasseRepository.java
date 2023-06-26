package com.tjw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.entities.Table_turma;

public interface ClasseRepository extends JpaRepository<Table_turma, Long> {
	List<Table_turma> findByNameContaining(String email);
}
