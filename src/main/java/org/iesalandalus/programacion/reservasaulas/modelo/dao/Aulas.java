package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

public class Aulas {
	private List<Aula> coleccionAulas;
	private static final String NOMBRE_FICHERO_AULAS = "ficheros/aulas.dat";

	public Aulas() {
		coleccionAulas = new ArrayList<>();
	}

	public Aulas(Aulas aulas) {
		setAulas(aulas);
	}

	private void setAulas(Aulas aulas) {
		if (aulas == null) {
			throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
		} else {
			this.coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);
		}
	}

	private List<Aula> copiaProfundaAulas(List<Aula> listaAula) {
		List<Aula> aulasCopia = new ArrayList<>();
		for (Aula aula : listaAula) {
			aulasCopia.add(new Aula(aula));
		}
		return aulasCopia;
	}

	public List<Aula> getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}

	public int getNumAulas() {
		return this.coleccionAulas.size();
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
		} else if (coleccionAulas.contains(aula)) {
			throw new OperationNotSupportedException("El aula ya existe.");
		} else {
			coleccionAulas.add(new Aula(aula));
		}

	}

	public Aula buscar(Aula aula) {
		int indiceAula = coleccionAulas.indexOf(aula);
		if (indiceAula != -1) {
			return new Aula(coleccionAulas.get(indiceAula));
		} else {
			return null;
		}
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		}
		if (!coleccionAulas.remove(aula)) {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}
	}

	public List<String> representar() {
		List<String> listaString = new ArrayList<>();
		for (Aula aula : coleccionAulas) {
			listaString.add(aula.toString());
		}
		return listaString;
	}

	public void leer() {
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAulas))) {
			Aula aula = null;
			do {
				aula = (Aula) entrada.readObject();
				insertar(aula);
			} while (aula != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido encontrar la aula para leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el fichero de aulas.");
		} catch (EOFException e) {
			System.out.println("El fichero de aulas ha sido leido.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void escribir() {
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAulas))) {
			for (Aula aula : coleccionAulas) {
				salida.writeObject(aula);
			}
			System.out.println("Fichero de aulas ha sido escrito");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede crear el fichero de aulas.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
}
