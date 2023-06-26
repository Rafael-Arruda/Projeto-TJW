package com.tjw.serviceExceptions;

@SuppressWarnings(value = { "serial" })
public class EmailNotFoundException extends RuntimeException {
	public EmailNotFoundException(String mail) {
		super("Não foi possível encontrar o email " + mail);
	}

}
