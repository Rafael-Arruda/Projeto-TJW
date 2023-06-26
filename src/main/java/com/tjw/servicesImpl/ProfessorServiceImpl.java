package com.tjw.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.tjw.dtos.response.ClasseDto;
import com.tjw.dtos.response.ProfessorDto;
import com.tjw.entities.Table_turma;
import com.tjw.entities.Table_professores;
import com.tjw.repositories.ProfessorRepository;
import com.tjw.serviceExceptions.NotFoundException;
import com.tjw.services.ProfessorService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ProfessorServiceImpl extends SimpleJpaRepository<Table_professores, Long> implements ProfessorService {
	@Autowired
	private ProfessorRepository professorRepository;

	public ProfessorServiceImpl(EntityManager entityManager) {
		super(Table_professores.class, entityManager);
	}

	@Override
	public Table_professores findByIdVerify(Long id) {
		return this.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	@Transactional
	public void update(Long id, Table_professores professor) {
		Table_professores professorFind = this.findByIdVerify(id);

		professorFind.setName(professor.getName());
		professorFind.setEmail(professor.getEmail());

		this.save(professorFind);
	}

	@Override
	public ProfessorDto findByIdAndClasses(Long id) {
		Table_professores professor = this.findByIdVerify(id);

		ProfessorDto professorDto = new ProfessorDto();
		professorDto.setId(professor.getId());
		professorDto.setName(professor.getName());
		professorDto.setEmail(professor.getEmail());

		List<ClasseDto> classesDto = new ArrayList<>();
		for (Table_turma classe : professor.getClasses()) {
			ClasseDto classeDto = new ClasseDto();
			classeDto.setId(classe.getId());
			classeDto.setName(classe.getName());
			classesDto.add(classeDto);
		}

		professorDto.setClasses(classesDto);

		return professorDto;
	}

	@Override
	public List<Table_professores> searchByEmail(String email) {
		return this.professorRepository.findByEmailContaining(email);
	}
}
