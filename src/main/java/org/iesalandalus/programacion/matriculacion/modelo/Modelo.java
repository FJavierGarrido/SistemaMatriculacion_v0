package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;

import javax.naming.OperationNotSupportedException;



public class Modelo {

    private static final int CAPACIDAD=3;
    private Alumnos alumnos;
    private Matriculas matriculas;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;



    public void comenzar(){
        //Instancias de las clases de negocio
        this.alumnos = new Alumnos(CAPACIDAD);
        this.asignaturas = new Asignaturas(CAPACIDAD);
        this.ciclosFormativos = new CiclosFormativos(CAPACIDAD);
        this.matriculas = new Matriculas(CAPACIDAD);
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public void insertar (Alumno alumno) throws Exception {
        if(alumno == null)
        {
            throw new Exception("ERROR: No se puede insertar un alumno nulo.");
        }
        alumnos.insertar(new Alumno(alumno)); //Copia profunda
    }

    public void insertar (Asignatura asignatura) throws Exception {
        if(asignatura == null)
        {
            throw new Exception("ERROR: No se puede insertar una asignatura nula.");
        }
        asignaturas.insertar(new Asignatura(asignatura)); //Copia profunda
    }

    public void insertar (CicloFormativo cicloFormativo) throws Exception {
        if(cicloFormativo == null)
        {
            throw new Exception("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        ciclosFormativos.insertar(new CicloFormativo(cicloFormativo)); //Copia profunda
    }

    public void insertar (Matricula matricula) throws Exception {
        if(matricula == null)
        {
            throw new Exception("ERROR: No se puede insertar una matricula nula.");
        }
        matriculas.insertar(new Matricula(matricula));  //Copia profunda
    }

    public Alumno buscar(Alumno alumno){
        Alumno alumnoEncontrado = alumnos.buscar(alumno);
        return (alumnoEncontrado != null) ? new Alumno(alumnoEncontrado) : null; //Copia profunda si existe, sino null
    }

    public Asignatura buscar(Asignatura asignatura){
        Asignatura asignaturaEncontrada = asignaturas.buscar(asignatura);
        return (asignaturaEncontrada != null) ? new Asignatura(asignaturaEncontrada) : null; //Copia profunda si existe, sino null
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo){
        CicloFormativo cicloFormativoEncontrado = ciclosFormativos.buscar(cicloFormativo);
        return (cicloFormativoEncontrado != null) ? new CicloFormativo(cicloFormativoEncontrado) : null; //Copia profunda si existe, sino null
    }

    public Matricula buscar(Matricula matricula){
        Matricula matriculaEncontrada = matriculas.buscar(matricula);
        return (matriculaEncontrada != null) ? new Matricula(matriculaEncontrada) : null; //Copia profunda si existe, sino null
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        alumnos.borrar(alumno);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        asignaturas.borrar(asignatura);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        ciclosFormativos.borrar(cicloFormativo);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        matriculas.borrar(matricula);
    }

    public Alumno[] getAlumnos() {
        return alumnos.get();
    }

    public Asignatura[] getAsignaturas(){
        return asignaturas.get();
    }

    public CicloFormativo[] getCiclosFormativos(){
        return ciclosFormativos.get();
    }

    public Matricula[] getMatriculas() throws OperationNotSupportedException {
        return matriculas.get();
    }

    public Matricula[] getMatriculas(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }

        return matriculas.get(alumno);
    }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo)  {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }

        return matriculas.get(cicloFormativo);
    }

    public Matricula[] getMatriculas(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico no puede ser nulo.");
        }

        return matriculas.get(cursoAcademico);
    }
}
