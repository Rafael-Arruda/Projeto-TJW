package com.tjw.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjw.dtos.response.ClasseDto;
import com.tjw.entities.Table_professores;
import com.tjw.servicesImpl.ClasseServiceImpl;
import com.tjw.servicesImpl.ProfessorServiceImpl;

@Controller
@RequestMapping("/turmas")
public class TurmasController {
	@Autowired
	private ClasseServiceImpl service;
	@Autowired
	private ProfessorServiceImpl professorService;

	@GetMapping("")
	public String turmas(Model model) {
		model.addAttribute("currentPage", "turmas");
		return "turmas";
	}

	@GetMapping("/cadastrar")
	public String cadastrarTurmas(Model model) {
		List<Table_professores> professors = professorService.findAll();
		model.addAttribute("currentPage", "turmas");
		model.addAttribute("professors", professors);
		return "cadastro-turma";
	}

	@GetMapping("/{id}")
	public String editarTurmas(@PathVariable Long id, Model model) {
		ClasseDto response = service.findByIdAndStudents(id);
		List<Table_professores> professors = professorService.findAll();
		model.addAttribute("classe", response);
		model.addAttribute("professors", professors);
		model.addAttribute("currentPage", "turmas");
		return "editar-turma";
	}

	@GetMapping("/matricula/{id}")
	public String matricularAlunos(@PathVariable Long id, Model model) {
		ClasseDto response = service.findByIdAndStudents(id);
		model.addAttribute("classe", response);
		model.addAttribute("currentPage", "turmas");
		return "enroll-students";
	}
}
