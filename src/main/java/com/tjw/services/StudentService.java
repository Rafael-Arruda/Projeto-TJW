package com.tjw.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.dtos.response.StudentDto;
import com.tjw.entities.Table_aluno;

public interface StudentService extends JpaRepository<Table_aluno, Long> {
	public Table_aluno findByIdVerify(Long id);

	public void update(Long id, Table_aluno student);

	public StudentDto findByIdAndClasses(Long id);

	List<Table_aluno> searchByEmail(String email);

	Table_aluno findOneByEmail(String email);
}
