package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.*;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.*;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;

public class ControladorReservasAulas implements IControladorReservasAulas {
	private IVistaReservasAulas vista;
	private IModeloReservasAulas modelo;
	
	public ControladorReservasAulas(IVistaReservasAulas vista, IModeloReservasAulas modelo) {
		this.vista = vista;
		this.modelo = modelo;
		vista.setControlador(this);
	}

	@Override
	public void comenzar() {
		vista.comenzar();
	}

	@Override
	public void salir() {
		vista.salir();
	}

	@Override
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		modelo.insertarAula(aula);
	}

	@Override
	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		modelo.borrarAula(aula);
	}

	@Override
	public Aula buscarAula(Aula aula) {
		return new Aula(modelo.buscarAula(aula));
	}

	@Override
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}

	@Override
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertarProfesor(profesor);
	}

	@Override
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrarProfesor(profesor);
	}

	@Override
	public Profesor buscarProfesor(Profesor profesor) {
		return new Profesor(modelo.buscarProfesor(profesor));

	}

	@Override
	public List<String> representarProfesores(){
		return modelo.representarProfesores();
	}

	@Override
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.realizarReserva(reserva);
	}

	@Override
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException{
		modelo.anularReserva(reserva);
	}

	@Override
	public List<String> representarReservas(){
		return modelo.representarReservas();
	}
	
	@Override
	public List<Reserva> getReservasAula(Aula aula){
		return modelo.getReservasAula(aula);
	}

	@Override
	public List<Reserva> getReservasProfesor(Profesor profesor){
		return modelo.getReservasProfesor(profesor);
	}

	@Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia){
		return modelo.getReservasPermanencia(permanencia);
	}

	@Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return modelo.consultarDisponibilidad(aula, permanencia);
	}
}

	