package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

public class Profesor {
	private static final String ER_TELEFONO = "[6|9]\\d{8}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	public Profesor(String nombre, String correo, String telefono) {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}

	public Profesor(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede copiar un profesor nulo.");
		}
		setNombre(profesor.nombre);
		setCorreo(profesor.correo);
		setTelefono(profesor.telefono);
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException("El nombre del profesor no puede ser nulo.");
		} else if (nombre.equals("")) {
			throw new IllegalArgumentException("El nombre del profesor no puede estar vacío.");
		} else {
			this.nombre = nombre;
		}
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new IllegalArgumentException("El correo del profesor no puede ser nulo.");
		} else if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("El correo del profesor no es válido.");
		} else {
			this.correo = correo;
		}
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			this.telefono = null;
		} else if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("El teléfono del profesor no es válido.");
		} else {
			this.telefono = telefono;
		}
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getCorreo() {
		return this.correo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (this.telefono == (null)) {
			return "[nombre=" + nombre + ", correo=" + correo + "]";
		} else {
			return "[nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + "]";
		}
	}

}
