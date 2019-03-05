package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.vista.Consola;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.VistaReservasAulas;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		Consola.mostrarCabecera("Realizado por Alejandro Gázquez Monedero");
		IVistaReservasAulas vista = new VistaReservasAulas();
		vista.comenzar();
	}

}
