package org.iesalandalus.programacion.reservasaulas.vista;

public enum Opcion {
	SALIR("SALIR") {
		public void ejecutar() {
			vista.salir();
		}
	},
	INSERTAR_AULA("INSERTAR AULA") {
		public void ejecutar() {
			vista.insertarAula();
		}
	},
	BORRAR_AULA("BORRAR AULA") {
		public void ejecutar() {
			vista.borrarAula();
		}
	},
	BUSCAR_AULA("BUSCAR AULA") {
		public void ejecutar() {
			vista.buscarAula();
		}
	},

	LISTAR_AULAS("LISTAR AULAS") {
		public void ejecutar() {
			vista.listarAulas();
		}
	},
	INSERTAR_PROFESOR("INSERTAR PROFESOR") {
		public void ejecutar() {
			vista.insertarProfesor();
		}
	},
	BORRAR_PROFESOR("BORRAR PROFESOR") {
		public void ejecutar() {
			vista.borrarProfesor();
		}
	},
	BUSCAR_PROFESOR("BUSCAR PROFESOR") {
		public void ejecutar() {
			vista.buscarProfesor();
		}
	},
	LISTAR_PROFESORES("LISTAR PROFESORES") {
		public void ejecutar() {
			vista.listarProfesores();
		}
	},
	INSERTAR_RESERVA("INSERTAR RESERVA") {
		public void ejecutar() {
			vista.realizarReserva();
		}
	},
	BORRAR_RESERVA("BORRAR RESERVA") {
		public void ejecutar() {
			vista.anularReserva();
		}
	},
	LISTAR_RESERVAS("LISTAR RESERVAS") {
		public void ejecutar() {
			vista.listarReservas();
		}
	},
	LISTAR_RESERVAS_AULA("LISTAR RESERVAS AULA") {
		public void ejecutar() {
			vista.listarReservasAula();
		}
	},
	LISTAR_RESERVAS_PROFESOR("LISTAR RESERVAS PROFESOR") {
		public void ejecutar() {
			vista.listarReservasProfesor();
		}
	},
	LISTAR_RESERVAS_PERMANENCIA("LISTAR RESERVAS PERMANECIA") {
		public void ejecutar() {
			vista.listarReservasPermanencia();
		}
	},
	CONSULTAR_DISPONIBILIDAD("CONSULTAR DISPONIBILIDAD") {
		public void ejecutar() {
			vista.consultarDisponibilidad();
		}
	};
	private String mensajeAMostrar;
	private static IVistaReservasAulas vista;

	private Opcion(String opcion) {
		this.mensajeAMostrar = opcion;
	}

	public String getMensaje() {
		return mensajeAMostrar;
	}

	public abstract void ejecutar();

	protected static void setVista(IVistaReservasAulas vista) {
		Opcion.vista = vista;
	}

	@Override
	public String toString() {
		return String.format("%d.-%s,", ordinal(), getMensaje());
	}

	public static Opcion getOpcionSegunOrdinal(int numeroEscogido) {
		if (esOrdinalValido(numeroEscogido)) {
			return values()[numeroEscogido];
		} else {
			throw new IllegalArgumentException("El numero escogido no es válido");
		}
	}

	public static boolean esOrdinalValido(int numero) {
		return (numero >= 0 && numero <= values().length - 1);
	}
}
