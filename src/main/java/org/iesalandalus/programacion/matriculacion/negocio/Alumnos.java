package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {
    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;


    // Constructor con parámetros
    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }

    // Método get para obtener una copia profunda de la colección
    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    // Método que realiza una copia profunda de la colección de alumnos
    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copia = new Alumno[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copia;
    }

    // Método para obtener el tamaño
    public int getTamano() {
        return tamano;
    }

    // Método para obtener la capacidad
    public int getCapacidad() {
        return capacidad;
    }

    // Método para insertar un alumno
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }

        if (buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }

        if (capacidadSuperada(capacidad)) {
            throw new OperationNotSupportedException("ERROR:C No se aceptan más alumnos.");
        }
        if (tamanoSuperado(tamano)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }

        coleccionAlumnos[tamano] = new Alumno(alumno);
        tamano++;
    }

    // Método para buscar el índice de un alumno
    private int buscarIndice(Alumno alumno) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                return i;
            }
        }
        return -1;
    }

    // Método para comprobar si el tamaño supera el límite de la capacidad
    private boolean tamanoSuperado(int indice) {
        return indice >= capacidad;
    }

    // Método para comprobar si el tamaño supera el límite de la capacidad
    private boolean capacidadSuperada(int indice) {
        return indice > capacidad;
    }

    // Método para buscar un alumno
    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                return coleccionAlumnos[i];
            }
        }
        return null;
    }

    // Método para borrar un alumno
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }



    // Método para desplazar los elementos hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[tamano - 1] = null; // Limpiar el último elemento
    }
}
