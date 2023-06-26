package com.tjw.dtos.response;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClasseDto {
	private Long id;

	private String name;

	private ProfessorDto professor;

	private Set<StudentDto> students;
}
