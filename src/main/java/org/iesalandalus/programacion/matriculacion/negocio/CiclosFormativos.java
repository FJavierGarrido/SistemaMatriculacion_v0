package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;

public class CiclosFormativos {
    private int capacidad;
    private int tamano;

    private CicloFormativo[] coleccionCiclosFormativos;

    public CiclosFormativos(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    public CicloFormativo[] get(){
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos(){
        CicloFormativo[] copia = new CicloFormativo[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
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
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }

        if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }

        if (capacidadSuperada(capacidad)) {
            throw new OperationNotSupportedException("ERROR:C No se aceptan más cicloFormativo.");
        }
        if (tamanoSuperado(tamano)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }

        coleccionCiclosFormativos[tamano] = new CicloFormativo(cicloFormativo);
        tamano++;
    }

    // Método para buscar por índice
    private int buscarIndice(CicloFormativo cicloFormativo) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
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

    // Método para buscar un Ciclo Formativo
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un cicloFormativo nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return coleccionCiclosFormativos[i];
            }
        }
        return null;
    }

    // Método para borrar un Ciclo Formativo
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    // Método para desplazar los elementos hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        coleccionCiclosFormativos[tamano - 1] = null; // Limpiar el último elemento
    }
}
