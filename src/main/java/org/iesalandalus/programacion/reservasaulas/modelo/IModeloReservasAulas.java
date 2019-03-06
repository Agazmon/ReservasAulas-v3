package org.iesalandalus.programacion.reservasaulas.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

public interface IModeloReservasAulas {

	List<Aula> getAulas();

	int getNumAulas();

	List<String> representarAulas();

	Aula buscarAula(Aula aulaBuscar);

	void insertarAula(Aula aulaInsertar) throws OperationNotSupportedException;

	void borrarAula(Aula aulaBorrar) throws OperationNotSupportedException;
	
	void leerAulas();
	
	void escribirAulas();

	List<Profesor> getProfesores();

	int getNumProfesores();

	List<String> representarProfesores();

	Profesor buscarProfesor(Profesor profesorBuscar);

	void insertarProfesor(Profesor profesorInsertar) throws OperationNotSupportedException;

	void borrarProfesor(Profesor profesorBorrar) throws OperationNotSupportedException;
	
	void leerProfesor();
	
	void escribirProfesor();

	List<Reserva> getReservas();

	int getNumReservas();

	List<String> representarReservas();

	Reserva buscarReserva(Reserva reservaBuscar);

	void realizarReserva(Reserva reservaRealizar) throws OperationNotSupportedException;

	void anularReserva(Reserva reservaAnular) throws OperationNotSupportedException;

	List<Reserva> getReservasAula(Aula aulaReservas);

	List<Reserva> getReservasProfesor(Profesor profesorReservas);

	List<Reserva> getReservasPermanencia(Permanencia permanenciaReservas);

	boolean consultarDisponibilidad(Aula aula, Permanencia permanencia);

	void leerReservas();

	void escribirReservas();

}