package com.tjw.controllers.rest.Classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tjw.dtos.response.ClasseDto;
import com.tjw.entities.Table_turma;
import com.tjw.servicesImpl.ClasseServiceImpl;

@RestController
@RequestMapping(value = "/classes")
public class ClasseController {
	@Autowired
	private ClasseServiceImpl service;

	@GetMapping
	public List<Table_turma> index() {
		List<Table_turma> response = service.findAll();
		return response;
	}

	@GetMapping("/search")
	public List<Table_turma> searchByName(@RequestParam("email") String name) {
		return service.searchByName(name);
	}

	@PostMapping
	public ModelAndView store(Table_turma classe) {
		service.save(classe);
		return new ModelAndView("redirect:/turmas");
	}

	@GetMapping(value = "/{id}")
	public ClasseDto show(@PathVariable Long id) {
		ClasseDto response = service.findByIdAndStudents(id);
		return response;
	}

	@PutMapping(value = "/{id}")
	public ModelAndView update(@PathVariable Long id, Table_turma classe) {
		service.update(id, classe);
		return new ModelAndView("redirect:/turmas");
	}

	@DeleteMapping(value = "/{id}")
	public void destroy(@PathVariable Long id) {
		service.deleteById(id);
	}
}
