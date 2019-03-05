package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {
		// Constructor por defecto no hace nada.
	}

	public static void mostrarMenu() {

		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion.toString());
		}
	}

	public static void mostrarCabecera(String cabecera) {
		System.out.printf("%n%s%n", cabecera);
		String bordes = "%0" + cabecera.length() + "d%n";
		System.out.println(String.format(bordes, 0).replace("0", "_"));
	}

	public static int elegirOpcion() {
		int opcion;
		do {
			System.out.print("\nElige una opcion:");
			opcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(opcion));
		return opcion;
	}

	public static Aula leerAula() {
		System.out.print("Introduce el nombre del Aula: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce la capacidad del Aula: ");
		int capacidad = Entrada.entero();
		return new Aula(nombre, capacidad);
	}

	public static String leerNombreAula() {
		String nombreAula;
		do {
			System.out.print("Introduce el nombre del Aula: ");
			nombreAula = Entrada.cadena();
		} while (nombreAula.trim().equals(""));
		return nombreAula;
	}

	public static Profesor leerProfesor() {
		String nombre, correo, telefono = "";
		System.out.print("Introduce el nombre de el Profesor o Profesora: ");
		nombre = Entrada.cadena();
		System.out.print("Introduce el correo de el Profesor o Profesora: ");
		correo = Entrada.cadena();
		System.out.print("Introduce el telefono de el Profesor o Profesora (No obligatorio): ");
		telefono = Entrada.cadena();
		if (telefono.trim().equals("")) {
			Profesor profesor= new Profesor(nombre, correo);
			return new Profesor(profesor) ;
		} else {
			Profesor profesor = new Profesor(nombre, correo, telefono);
			System.out.println(profesor);
			return new Profesor(profesor);
		}
	}

	public static String leerNombreProfesor() {
		String nombre;
		do {
			System.out.print("Introduce el nombre de el Profesor o Profesora: ");
			nombre = Entrada.cadena();
		} while (nombre.trim().equals(""));
		return nombre;
	}

	public static Tramo leerTramo() {
		String seleccion;
		System.out.print("Introduce el tramo (Mañana o Tarde): ");
		seleccion = Entrada.cadena();
		if (seleccion.equals("Mañana")) {
			return Tramo.MANANA;
		} else if (seleccion.equals("Tarde")) {
			return Tramo.TARDE;
		} else {
			return null;
		}

	}

	public static String leerDia() {
		int dia, mes, anio;
		do {
			System.out.print("Introduce el dia: ");
			dia = Entrada.entero();
			System.out.print("Introduce el mes: ");
			mes = Entrada.entero();
			System.out.print("Introduce el año: ");
			anio = Entrada.entero();
			;
		} while (LocalDate.of(anio, mes, dia).isBefore(LocalDate.now()));
		return LocalDate.of(anio, mes, dia).format(FORMATO_DIA);
	}
	public static LocalTime leerHora() {
		int hora, minutos;
		minutos = 00;
		do {
			System.out.print("Introduce una hora entre las 8 y las 22, la reserva se hará a en punto: ");
			hora = Entrada.entero();
		} while (hora<8 || hora>22);
		return LocalTime.of(hora, minutos);
	}
	public static Permanencia leerPermanencia() {
		if(elegirPermanencia()==1) {
			return (Permanencia) new PermanenciaPorTramo(leerDia(), leerTramo());
		} else {
			return (Permanencia) new PermanenciaPorHora(leerDia(), leerHora());
		}
	}
	public static int elegirPermanencia() {
		int eleccion=0;
		do {
			System.out.print("Elige el tipo de permanencia, 1=Por Tramo ; 2=Por Hora: ");
			eleccion=Entrada.entero();
		} while (eleccion>2|eleccion<1);
		return eleccion;
	}
}
