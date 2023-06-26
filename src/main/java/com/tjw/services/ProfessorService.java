package com.tjw.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.dtos.response.ProfessorDto;
import com.tjw.entities.Table_professores;

public interface ProfessorService extends JpaRepository<Table_professores, Long> {
	public Table_professores findByIdVerify(Long id);

	public void update(Long id, Table_professores professor);

	public ProfessorDto findByIdAndClasses(Long id);

	List<Table_professores> searchByEmail(String email);
}
