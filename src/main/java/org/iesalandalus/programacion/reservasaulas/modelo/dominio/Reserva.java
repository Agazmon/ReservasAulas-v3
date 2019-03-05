package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.*;

public class Reserva implements Serializable{
	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;

	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}

	public Reserva(Reserva reserva) {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede copiar una reserva nula.");
		}
		setProfesor(reserva.profesor);
		setAula(reserva.aula);
		setPermanencia(reserva.permanencia);
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
		} else {
			this.profesor = new Profesor(profesor);
		}
	}

	public Profesor getProfesor() {
		return new Profesor(this.profesor);
	}

	private void setAula(Aula aula) {
		if (aula == null) {
			throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
		} else {
			this.aula = new Aula(aula);
		}
	}

	public Aula getAula() {
		return new Aula(this.aula);
	}

	private void setPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
		} else {
			if (permanencia instanceof PermanenciaPorTramo) {
				this.permanencia = new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
			} else {
				this.permanencia = new PermanenciaPorHora((PermanenciaPorHora) permanencia);
			}
		}
	}

	public Permanencia getPermanencia() {
		if (this.permanencia instanceof PermanenciaPorTramo) {
			return new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
		} else {
			return new PermanenciaPorHora((PermanenciaPorHora) permanencia);
		}
	}

	public float getPuntos() {
		return permanencia.getPuntos() + aula.getPuntos();
	}

	@Override
	public int hashCode() {
		return Objects.hash(aula, permanencia, profesor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Reserva)) {
			return false;
		}
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia);
	}

	@Override
	public String toString() {
		return String.format("[profesor=%s, aula=%s, permanencia=%s, puntos=%s]", profesor, aula, permanencia,
				getPuntos());
	}

}
