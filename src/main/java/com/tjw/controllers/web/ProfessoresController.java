package com.tjw.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjw.dtos.response.ProfessorDto;
import com.tjw.servicesImpl.ProfessorServiceImpl;

@Controller
@RequestMapping("/professores")
public class ProfessoresController {
	@Autowired
	private ProfessorServiceImpl service;

	@GetMapping("")
	public String professores(Model model) {
		model.addAttribute("currentPage", "professores");
		return "professores";
	}

	@GetMapping("/cadastrar")
	public String cadastrarProfessores(Model model) {
		model.addAttribute("currentPage", "professores");
		return "cadastro-professor";
	}

	@GetMapping("/{id}")
	public String editarProfessores(@PathVariable Long id, Model model) {
		ProfessorDto response = service.findByIdAndClasses(id);
		model.addAttribute("professor", response);
		model.addAttribute("currentPage", "professores");
		return "editar-professor";
	}

	@GetMapping("/turmas/{id}")
	public String matricularAlunos(@PathVariable Long id, Model model) {
		ProfessorDto response = service.findByIdAndClasses(id);
		model.addAttribute("professor", response);
		model.addAttribute("currentPage", "professores");
		return "professores-turmas";
	}
}
