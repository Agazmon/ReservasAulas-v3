package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;

public class Reservas {

	private List<Reserva> coleccionReservas;
	private float MAX_PUNTOS_PROFESOR_MES = 200.00f;
	private static final String NOMBRE_FICHERO_RESERVAS = "fichero/reservas.dat";

	public Reservas() {
		coleccionReservas = new ArrayList<>();
	}

	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}

	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
	}

	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> otrasReservas = new ArrayList<>();
		for (Reserva reserva : reservas) {
			otrasReservas.add(new Reserva(reserva));
		}
		return otrasReservas;
	}

	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return coleccionReservas.size();
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		}
		if (esMesSiguienteOPosterior(reserva)) {
			throw new OperationNotSupportedException(
					"Sólo se pueden hacer reservas para el mes que viene o posteriores.");
		}
		if (getPuntosGastadosReserva(reserva) > MAX_PUNTOS_PROFESOR_MES) {
			throw new OperationNotSupportedException(
					"Esta reserva excede los puntos máximos por mes para dicho profesor.");
		}
		if (coleccionReservas.contains(reserva)) {
			throw new OperationNotSupportedException("La reserva ya existe.");
		} else {
			if (getReservaDia(reserva.getPermanencia().getDia()) == null) {
				if (consultarDisponibilidad(reserva.getAula(), reserva.getPermanencia())) {
					coleccionReservas.add(new Reserva(reserva));
				}
			} else {
				if (getReservaDia(reserva.getPermanencia().getDia()).getPermanencia() instanceof PermanenciaPorTramo
						& reserva.getPermanencia() instanceof PermanenciaPorTramo) {
					if (consultarDisponibilidad(reserva.getAula(), reserva.getPermanencia())) {
						coleccionReservas.add(new Reserva(reserva));
					}
				} else {
					if (getReservaDia(reserva.getPermanencia().getDia()).getPermanencia() instanceof PermanenciaPorHora
							& reserva.getPermanencia() instanceof PermanenciaPorHora) {
						if (consultarDisponibilidad(reserva.getAula(), reserva.getPermanencia())) {
							coleccionReservas.add(new Reserva(reserva));
						}
					} else {
						if (getReservaDia(reserva.getPermanencia().getDia())
								.getPermanencia() instanceof PermanenciaPorHora) {
							throw new OperationNotSupportedException(
									"Ya se ha realizado una reserva por hora para este día y aula.");
						} else {
							throw new OperationNotSupportedException(
									"Ya se ha realizado una reserva por tramo para este día y aula.");
						}
					}
				}
			}
		}
	}

	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		if (reserva.getPermanencia().getDia().getMonthValue() == (LocalDate.now().getMonthValue())) {
			return true;
		} else {
			return false;
		}
	}

	private float getPuntosGastadosReserva(Reserva reserva) {
		float totalPuntos = 0;
		if (getReservasProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia()) == null) {
			return reserva.getPuntos();
		} else {
			for (Reserva puntosReservas : getReservasProfesorMes(reserva.getProfesor(),
					reserva.getPermanencia().getDia())) {
				totalPuntos += puntosReservas.getPuntos();
			}
			return totalPuntos + reserva.getPuntos();
		}
	}

	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate fecha) {
		List<Reserva> reservasProfesor = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getProfesor().equals(profesor)
					& reserva.getPermanencia().getDia().getMonthValue() == fecha.getMonthValue()) {
				reservasProfesor.add(new Reserva(reserva));
			}
		}
		return reservasProfesor;
	}

	private Reserva getReservaDia(LocalDate fecha) {
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getPermanencia().getDia().equals(fecha)) {
				return new Reserva(reserva);
			}
		}
		return null;

	}

	public Reserva buscar(Reserva reserva) {
		int indice = coleccionReservas.indexOf(reserva);
		if (indice != -1) {
			return new Reserva(coleccionReservas.get(indice));
		} else {
			return null;
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		if (!coleccionReservas.remove(reserva)) {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}

	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			representacion.add(new Reserva(reserva).toString());
		}
		return representacion;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {
		List<Reserva> reservasProfesor = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getProfesor().equals(profesor)) {
				reservasProfesor.add(new Reserva(reserva));
			}
		}
		return reservasProfesor;
	}

	public List<Reserva> getReservasAula(Aula aula) {
		List<Reserva> reservasAula = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getAula().equals(aula)) {
				reservasAula.add(new Reserva(reserva));
			}
		}
		return reservasAula;
	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		List<Reserva> reservasPermanencia = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getPermanencia().equals(permanencia)) {
				reservasPermanencia.add(new Reserva(reserva));
			}
		}
		return reservasPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		}
		if (permanencia == null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		}
		if (getReservaDia(permanencia.getDia()) == null) {
			return true;
		} else {
			Profesor profesorConsulta = new Profesor("Profesor", "correo@correo.com");
			Reserva reservaConsulta = new Reserva(profesorConsulta, aula, permanencia);
			if (coleccionReservas.contains(reservaConsulta)) {
				for (Reserva reserva : coleccionReservas) {
					if ((getReservaDia(reserva.getPermanencia().getDia())
							.getPermanencia() instanceof PermanenciaPorTramo
							& reserva.getPermanencia() instanceof PermanenciaPorTramo)
							|| (getReservaDia(reserva.getPermanencia().getDia())
									.getPermanencia() instanceof PermanenciaPorHora
									& reserva.getPermanencia() instanceof PermanenciaPorHora)) {
						if (reserva.getPermanencia().equals(reservaConsulta.getPermanencia())) {
							return false;
						}
					}

				}
				return true;
			} else {
				return true;
			}
		}
	}

	public void leer() {
		File ficheroReservas = new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroReservas))) {
			Reserva reserva = null;
			do {
				reserva = (Reserva) entrada.readObject();
				insertar(reserva);
			} while (reserva != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido encontrar la clase para leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el fichero de reservas.");
		} catch (EOFException e) {
			System.out.println("El fichero de reservas ha sido leido.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void escribir() {
		File ficheroReservas = new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroReservas))) {
			for (Reserva reserva : coleccionReservas) {
				salida.writeObject(reserva);
			}
			System.out.println("Fichero de reservas ha sido escrito");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede crear el fichero de reservas.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
}