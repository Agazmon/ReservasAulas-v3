package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class PermanenciaPorHora extends Permanencia implements Serializable {
	private static final int PUNTOS = 3;
	private static final LocalTime HORA_INICIO = LocalTime.of(8, 00);
	private static final LocalTime HORA_FIN = LocalTime.of(22, 00);
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalTime hora;

	public PermanenciaPorHora(LocalDate dia, LocalTime hora) {
		super(dia);
		setHora(hora);
	}

	public PermanenciaPorHora(String dia, LocalTime hora) {
		super(dia);
		setHora(hora);
	}

	public PermanenciaPorHora(LocalDate dia, String hora) {
		super(dia);
		setHora(hora);
	}

	public PermanenciaPorHora(String dia, String hora) {
		super(dia);
		setHora(hora);
	}

	public PermanenciaPorHora(PermanenciaPorHora permanencia) {
		super();
		if (permanencia == null) {
			throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
		} else {
			setDia(permanencia.dia);
			setHora(permanencia.hora);
		}
	}

	public LocalTime getHora() {
		return this.hora;
	}

	private void setHora(LocalTime hora) {
		if (hora == null) {
			throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
		}
		if (hora.getHour() > HORA_FIN.getHour() || hora.getHour() < HORA_INICIO.getHour()) {
			throw new IllegalArgumentException(
					"La hora de una permanencia debe estar comprendida entre las 8 y las 22.");
		}
		if (hora.getMinute() != 00) {
			throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");
		} else {
			this.hora = hora;
		}
	}

	private void setHora(String hora) {
		if (hora == null || hora.trim().equals("")) {
			throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
		} else {
			try {
				if (LocalTime.parse(hora, FORMATO_HORA).isBefore(HORA_INICIO)
						| LocalTime.parse(hora, FORMATO_HORA).isAfter(HORA_FIN)) {
					throw new IllegalArgumentException(
							"La hora de una permanencia debe estar comprendida entre las 8 y las 22.");
				}
				if (LocalTime.parse(hora, FORMATO_HORA).getMinute() != 00) {
					throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");
				} else {
					this.hora = LocalTime.parse(hora, FORMATO_HORA);
				}
			} catch (DateTimeParseException e) {
				throw new IllegalArgumentException("El formato de la hora de la permanencia no es correcto.");
			}

		}
	}

	@Override
	public int getPuntos() {
		return PUNTOS;
	}

	
		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PermanenciaPorHora))
			return false;
		PermanenciaPorHora other = (PermanenciaPorHora) obj;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora)) {
			return false;
		}
		Permanencia other2 = (Permanencia) obj;
		if (dia == null) {
			if (other2.dia != null)
				return false;
		} else if (!dia.equals(other2.dia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("[dia=%s, hora=%s]", dia.format(FORMATO_DIA), hora.format(FORMATO_HORA));
	}

}
