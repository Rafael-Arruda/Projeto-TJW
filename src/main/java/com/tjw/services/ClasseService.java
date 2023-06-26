package com.tjw.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.dtos.response.ClasseDto;
import com.tjw.entities.Table_turma;
import com.tjw.entities.Table_aluno;

public interface ClasseService extends JpaRepository<Table_turma, Long> {
	public Table_turma findByIdVerify(Long id);

	public void update(Long id, Table_turma classe);

	public ClasseDto findByIdAndStudents(Long id);

	public void enroll(Long id, Set<Table_aluno> students);

	List<Table_turma> searchByName(String name);
}
