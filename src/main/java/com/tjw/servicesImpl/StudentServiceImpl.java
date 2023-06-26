package com.tjw.servicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.tjw.dtos.response.ClasseDto;
import com.tjw.dtos.response.StudentDto;
import com.tjw.entities.Table_turma;
import com.tjw.entities.Table_aluno;
import com.tjw.repositories.StudentRepository;
import com.tjw.serviceExceptions.EmailNotFoundException;
import com.tjw.serviceExceptions.NotFoundException;
import com.tjw.services.StudentService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl extends SimpleJpaRepository<Table_aluno, Long> implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public StudentServiceImpl(EntityManager entityManager) {
		super(Table_aluno.class, entityManager);
	}

	@Override
	public Table_aluno findByIdVerify(Long id) {
		return this.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	@Transactional
	public void update(Long id, Table_aluno student) {
		Table_aluno studentFind = this.findByIdVerify(id);

		studentFind.setName(student.getName());
		studentFind.setEmail(student.getEmail());

		this.save(studentFind);
	}

	@Override
	public StudentDto findByIdAndClasses(Long id) {
		Table_aluno student = this.findByIdVerify(id);

		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setEmail(student.getEmail());

		Set<ClasseDto> classesDto = new HashSet<>();
		for (Table_turma classe : student.getClasses()) {
			ClasseDto classeDto = new ClasseDto();
			classeDto.setId(classe.getId());
			classeDto.setName(classe.getName());
			classesDto.add(classeDto);
		}

		studentDto.setClasses(classesDto);

		return studentDto;
	}

	@Override
	public List<Table_aluno> searchByEmail(String email) {
		return this.studentRepository.findByEmailContaining(email);
	}

	@Override
	public Table_aluno findOneByEmail(String email) {
		return this.studentRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException(email));
	}
}
