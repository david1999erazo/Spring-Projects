package com.example.thymeleafMIO.exceptions;

public class InconsistentDateException extends Exception {

	public InconsistentDateException() {
		super("Fechas no consistentes");
	}
}
