package com.tjw.dtos.response;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
	private Long id;

	private String name;

	private String email;

	private Set<ClasseDto> classes;
}
