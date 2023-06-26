package com.tjw.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
// import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Table_aluno")
public class Table_aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Setter(AccessLevel.NONE)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
	@JsonIgnore
	// @JoinTable(name = "students_classes", joinColumns = @JoinColumn(name =
	// "student_id"), inverseJoinColumns = @JoinColumn(name = "classe_id"))
	private Set<Table_turma> classes;

	@Deprecated
	public Table_aluno() {
	}
}
