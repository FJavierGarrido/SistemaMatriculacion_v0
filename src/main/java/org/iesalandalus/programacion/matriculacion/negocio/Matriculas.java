package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;


public class Matriculas {
    private int capacidad;
    private int tamano;

    private Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }

    public Matricula[] get() throws OperationNotSupportedException {
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas(){
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]);
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
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }

        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }

        if (capacidadSuperada(capacidad)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        if (tamanoSuperado(tamano)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }

        coleccionMatriculas[tamano] = new Matricula(matricula);
        tamano++;
    }

    // Método para buscar por índice
    private int buscarIndice(Matricula matricula) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
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
    // Método para buscar una Matrícula
    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar un matrícula nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                return coleccionMatriculas[i];
            }
        }
        return null;
    }

    // Método para borrar un matrícula
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    // Método para desplazar los elementos hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[tamano - 1] = null; // Limpiar el último elemento
    }

    // Método para obtener las matrículas de un alumno específico
    public Matricula[] get(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }

        // Contar cuántas matrículas pertenecen al alumno
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                contador++;
            }
        }

        // Crear el array con el tamaño adecuado
        Matricula[] resultado = new Matricula[contador];
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                resultado[indice++] = coleccionMatriculas[i];
            }
        }

        return resultado;
    }

    // Método para obtener las matrículas de un curso académico específico
    public Matricula[] get(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico no puede ser nulo.");
        }

        // Contar cuántas matrículas pertenecen al curso académico
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                contador++;
            }
        }

        // Crear el array con el tamaño adecuado
        Matricula[] resultado = new Matricula[contador];
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                resultado[indice++] = coleccionMatriculas[i];
            }
        }

        return resultado;
    }

    // Método para obtener las matrículas de un ciclo formativo específico
    public Matricula[] get(CiclosFormativos ciclosFormativos) {
        if (ciclosFormativos == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }

        // Contamos cuántas matrículas pertenecen al ciclo formativo
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            // Recorremos las asignaturas de cada matrícula
            Asignatura[] asignaturas = coleccionMatriculas[i].getColeccionAsignaturas();  // Obtener asignaturas de la matrícula
            for (Asignatura asignatura : asignaturas) {
                if (asignatura != null && asignatura.getCicloFormativo().equals(ciclosFormativos)) {
                    contador++;
                    break; // Si encontramos una asignatura del ciclo, no necesitamos seguir buscando en esa matrícula
                }
            }
        }

        // Creamos el array con el tamaño adecuado
        Matricula[] resultado = new Matricula[contador];
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            // Recorremos nuevamente las asignaturas de cada matrícula
            Asignatura[] asignaturas = coleccionMatriculas[i].getColeccionAsignaturas();  // Obtener asignaturas de la matrícula
            for (Asignatura asignatura : asignaturas) {
                if (asignatura != null && asignatura.getCicloFormativo().equals(ciclosFormativos)) {
                    resultado[indice++] = coleccionMatriculas[i];
                    break; // Ya hemos agregado la matrícula, no necesitamos seguir buscando en esa matrícula
                }
            }
        }

        return resultado;
    }



}
