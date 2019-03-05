package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Permanencia {
	protected LocalDate dia;
	protected static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	protected Permanencia() {

	}

	protected Permanencia(LocalDate fechaPasada){
		setDia(fechaPasada);
	}

	protected Permanencia(String cadenaPermanencia) {
		if (cadenaPermanencia == null) {
			throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
		} else if (cadenaPermanencia.trim().equals("")) {
			throw new IllegalArgumentException("La cadena no puede ser vacia");
		} else {
			setDia(cadenaPermanencia);
		}
	}

	public LocalDate getDia() {
		return dia;
	}

	protected void setDia(LocalDate diaPasado) {
		if (diaPasado == null) {
			throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
		} else {
				this.dia = diaPasado;
		}
	}

	protected void setDia(String cadenaPermanencia) {
		if (cadenaPermanencia == null || cadenaPermanencia.trim().equals("")) {
			throw new IllegalArgumentException("Fecha pasada no válida");
		} else {
			try {
				this.dia = LocalDate.parse(cadenaPermanencia, FORMATO_DIA);
			} catch (DateTimeParseException e) {
				throw new IllegalArgumentException("El formato del día de la permanencia no es correcto.");
			}
		}
	}

	public abstract int getPuntos();
	
	@Override
	public abstract String toString();
	@Override
	public abstract int hashCode();
	@Override
	public abstract boolean equals(Object obj);

}
