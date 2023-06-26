package com.tjw.servicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.tjw.dtos.response.ClasseDto;
import com.tjw.dtos.response.ProfessorDto;
import com.tjw.dtos.response.StudentDto;
import com.tjw.entities.Table_turma;
import com.tjw.entities.Table_aluno;
import com.tjw.repositories.ClasseRepository;
import com.tjw.serviceExceptions.NotFoundException;
import com.tjw.services.ClasseService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ClasseServiceImpl extends SimpleJpaRepository<Table_turma, Long> implements ClasseService {
	@Autowired
	private ClasseRepository classeRepository;

	public ClasseServiceImpl(EntityManager entityManager) {
		super(Table_turma.class, entityManager);
	}

	@Override
	public Table_turma findByIdVerify(Long id) {
		return this.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	@Transactional
	public void update(Long id, Table_turma classe) {
		Table_turma classeFind = this.findByIdVerify(id);

		classeFind.setName(classe.getName());
		if (classe.getProfessor() != null) {
			classeFind.setProfessor(classe.getProfessor());
		}

		this.save(classeFind);
	}

	@Override
	public ClasseDto findByIdAndStudents(Long id) {
		Table_turma classe = this.findByIdVerify(id);
		ClasseDto classeDto = new ClasseDto();
		classeDto.setId(classe.getId());
		classeDto.setName(classe.getName());

		ProfessorDto professorDto = new ProfessorDto();
		professorDto.setId(classe.getProfessor().getId());
		professorDto.setName(classe.getProfessor().getName());
		professorDto.setEmail(classe.getProfessor().getEmail());

		classeDto.setProfessor(professorDto);

		Set<StudentDto> studentsDto = new HashSet<>();
		for (Table_aluno student : classe.getStudents()) {
			StudentDto studentDto = new StudentDto();
			studentDto.setId(student.getId());
			studentDto.setName(student.getName());
			studentDto.setEmail(student.getEmail());
			studentsDto.add(studentDto);
		}

		classeDto.setStudents(studentsDto);

		return classeDto;
	}

	@Override
	@Transactional
	public void enroll(Long id, Set<Table_aluno> students) {
		Table_turma classe = this.findByIdVerify(id);
		classe.getStudents().removeIf(student -> !students.contains(student));

		classe.getStudents().addAll(students);

		this.save(classe);
	}

	@Override
	public List<Table_turma> searchByName(String name) {
		return this.classeRepository.findByNameContaining(name);
	}
}
