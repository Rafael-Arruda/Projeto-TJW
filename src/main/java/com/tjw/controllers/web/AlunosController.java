package com.tjw.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjw.dtos.response.StudentDto;
import com.tjw.servicesImpl.StudentServiceImpl;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	@Autowired
	private StudentServiceImpl service;

	@GetMapping("")
	public String alunos(Model model) {
		model.addAttribute("currentPage", "alunos");
		return "alunos";
	}

	@GetMapping("/cadastrar")
	public String cadastrarAlunos(Model model) {
		model.addAttribute("currentPage", "alunos");
		return "cadastro-aluno";
	}

	@GetMapping("/{id}")
	public String editarAlunos(@PathVariable Long id, Model model) {
		StudentDto response = service.findByIdAndClasses(id);
		model.addAttribute("student", response);
		model.addAttribute("currentPage", "alunos");
		return "editar-aluno";
	}

	@GetMapping("/turmas/{id}")
	public String matricularAlunos(@PathVariable Long id, Model model) {
		StudentDto response = service.findByIdAndClasses(id);
		model.addAttribute("student", response);
		model.addAttribute("currentPage", "alunos");
		return "alunos-turmas";
	}
}
