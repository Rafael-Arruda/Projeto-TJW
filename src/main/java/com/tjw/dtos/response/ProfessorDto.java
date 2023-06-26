package com.tjw.dtos.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDto {
	private Long id;

	private String name;

	private String email;

	private List<ClasseDto> classes;
}
