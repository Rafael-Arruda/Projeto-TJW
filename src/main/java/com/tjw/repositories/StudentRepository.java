package com.tjw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.entities.Table_aluno;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Table_aluno, Long> {
	List<Table_aluno> findByEmailContaining(String email);

	Optional<Table_aluno> findByEmail(String email);
}
