package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class PermanenciaPorTramo extends Permanencia implements Serializable{
	private Tramo tramo;
	private static final int PUNTOS = 10;

	public PermanenciaPorTramo(LocalDate dia, Tramo tramo) {
		super(dia);
		setTramo(tramo);
	}

	public PermanenciaPorTramo(String diaString, Tramo tramo) {
		super(diaString);
		setTramo(tramo);
	}

	public PermanenciaPorTramo(PermanenciaPorTramo permanenciaTramo) {
		super();
		if (permanenciaTramo == null) {
			throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
		} else {
			setDia(permanenciaTramo.dia);
			setTramo(permanenciaTramo.tramo);
		}
	
	}

	public Tramo getTramo() {
		return this.tramo;
	}

	private void setTramo(Tramo tramo) {
		if (tramo == null) {
			throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
		} else {
			this.tramo = tramo;
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
		result = prime * result + ((tramo == null) ? 0 : tramo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PermanenciaPorTramo))
			return false;
		PermanenciaPorTramo other = (PermanenciaPorTramo) obj;
		if (tramo == null) {
			if (other.tramo != null)
				return false;
		} else if (!tramo.equals(other.tramo)) {
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
		return String.format("[dia=%s, tramo=%s]", dia.format(FORMATO_DIA), tramo);
	}

}
