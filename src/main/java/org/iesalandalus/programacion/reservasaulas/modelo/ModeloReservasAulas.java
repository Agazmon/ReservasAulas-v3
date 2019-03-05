package org.iesalandalus.programacion.reservasaulas.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dao.*;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

public class ModeloReservasAulas implements IModeloReservasAulas {
	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;

	public ModeloReservasAulas() {
		profesores = new Profesores();
		aulas = new Aulas();
		reservas = new Reservas();
	}

	@Override
	public List<Aula> getAulas() {
		return aulas.getAulas();
	}

	@Override
	public int getNumAulas() {
		return aulas.getNumAulas();
	}

	@Override
	public List<String> representarAulas() {
		return aulas.representar();
	}

	@Override
	public Aula buscarAula(Aula aulaBuscar) {
		return aulas.buscar(aulaBuscar);
	}

	@Override
	public void insertarAula(Aula aulaInsertar) throws OperationNotSupportedException {
		aulas.insertar(aulaInsertar);
	}

	@Override
	public void borrarAula(Aula aulaBorrar) throws OperationNotSupportedException {
		aulas.borrar(aulaBorrar);
	}

	@Override
	public List<Profesor> getProfesores() {
		return profesores.getProfesores();
	}

	@Override
	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}

	@Override
	public List<String> representarProfesores() {
		return profesores.representar();
	}

	@Override
	public Profesor buscarProfesor(Profesor profesorBuscar) {
		return profesores.buscar(profesorBuscar);
	}

	@Override
	public void insertarProfesor(Profesor profesorInsertar) throws OperationNotSupportedException {
		profesores.insertar(profesorInsertar);
	}

	@Override
	public void borrarProfesor(Profesor profesorBorrar) throws OperationNotSupportedException {
		profesores.borrar(profesorBorrar);
	}

	@Override
	public List<Reserva> getReservas() {
		return reservas.getReservas();
	}

	@Override
	public int getNumReservas() {
		return reservas.getNumReservas();
	}

	@Override
	public List<String> representarReservas() {
		return reservas.representar();
	}

	@Override
	public Reserva buscarReserva(Reserva reservaBuscar) {
		return reservas.buscar(reservaBuscar);
	}

	@Override
	public void realizarReserva(Reserva reservaRealizar) throws OperationNotSupportedException {
		reservas.insertar(reservaRealizar);
	}

	@Override
	public void anularReserva(Reserva reservaAnular) throws OperationNotSupportedException {
		reservas.borrar(reservaAnular);
	}

	@Override
	public List<Reserva> getReservasAula(Aula aulaReservas) {
		return reservas.getReservasAula(aulaReservas);
	}

	@Override
	public List<Reserva> getReservasProfesor(Profesor profesorReservas) {
		return reservas.getReservasProfesor(profesorReservas);
	}

	@Override
	public List<Reserva> getReservasPermanencia(Permanencia permanenciaReservas) {
		return reservas.getReservasPermanencia(permanenciaReservas);
	}

	@Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}
}