package com.tjw.serviceExceptions;

@SuppressWarnings(value = { "serial" })
public class NotFoundException extends RuntimeException {
	public NotFoundException(Long id) {
		super("Não foi possível encontrar o id " + id);
	}

}
