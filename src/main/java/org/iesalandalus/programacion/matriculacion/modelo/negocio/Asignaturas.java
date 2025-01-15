package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;

public class Asignaturas {
    private int capacidad;
    private int tamano;

    private Asignatura[] coleccionAsignaturas;

    public Asignaturas (int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignatura[capacidad];
    }

    public Asignatura[] get(){
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas(){
        Asignatura[] copia = new Asignatura[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Asignatura(coleccionAsignaturas[i]);
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

    // Método para insertar
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }

        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }

        if (capacidadSuperada(capacidad)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }
        if (tamanoSuperado(tamano)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }

        coleccionAsignaturas[tamano] = new Asignatura(asignatura);
        tamano++;
    }

    // Método para buscar por índice
    private int buscarIndice(Asignatura asignatura) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
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

    // Método para buscar una Asignatura
    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar un asignatura nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
                return coleccionAsignaturas[i];
            }
        }
        return null;
    }

    // Método para borrar un asignatura
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    // Método para desplazar los elementos hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[tamano - 1] = null; // Limpiar el último elemento
    }

}
