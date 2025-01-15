package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;


public class Vista {

    private Controlador controlador;


    private void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        controlador.terminar();
    }


    private void ejecutarOpcion(Opcion opcion)  {

        switch (opcion) {
            case INSERTAR_ALUMNO:
                insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                break;

            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAsignaturas();
                break;

            case INSERTAR_CICLO_FORMATIVO:
                insertarCicloFormativo();
                break;
            case BUSCAR_CICLO_FORMATIVO:
                buscarCicloFormativo();
                break;
            case BORRAR_CICLO_FORMATIVO:
                borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                mostrarCiclosFormativos();
                break;

            case INSERTAR_MATRICULA:
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCursoAcademico();
                break;

            case SALIR:
                terminar();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void insertarAlumno(){
        try {
            Alumno alumno = new Alumno(Consola.leerAlumno());
            controlador.insertar(alumno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarAlumno(){
        try {
            Alumno alumno = new Alumno(Consola.leerAlumno());
            controlador.buscar(alumno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarAlumno(){
        try {
            Alumno alumno = new Alumno(Consola.leerAlumno());
            controlador.borrar(alumno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarAlumnos(){

        Alumno[] alumnos = controlador.getAlumnos();

        if (alumnos.length>0){
            for (Alumno a:alumnos){
                System.out.println(a);
            }
        }else {
            System.out.println("No hay alumnos.");
        }
    }

    private void insertarAsignatura(){
        try {
            Asignatura asignatura = new Asignatura(Consola.leerAsignatura());
            controlador.insertar(asignatura);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarAsignatura(){
        try {
            Asignatura asignatura = new Asignatura(Consola.leerAsignatura());
            controlador.buscar(asignatura);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarAsignatura(){
        try {
            Asignatura asignatura = new Asignatura(Consola.leerAsignatura());
            controlador.borrar(asignatura);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarAsignaturas(){

        Asignatura[] asignaturas = controlador.getAsignaturas();

        if (asignaturas.length>0){
            for (Asignatura a:asignaturas){
                System.out.println(a);
            }
        }else {
            System.out.println("No hay asignaturas.");
        }
    }

    private void insertarCicloFormativo(){
        try {
            CicloFormativo cicloFormativo = new CicloFormativo(Consola.leerCicloFormativo());
            controlador.insertar(cicloFormativo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCicloFormativo(){
        try {
            CicloFormativo cicloFormativo = new CicloFormativo(Consola.leerCicloFormativo());
            controlador.buscar(cicloFormativo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCicloFormativo(){
        try {
            CicloFormativo cicloFormativo = new CicloFormativo(Consola.leerCicloFormativo());
            controlador.borrar(cicloFormativo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarCiclosFormativos(){

        CicloFormativo[] ciclosFormativos = controlador.getCiclosFormativos();

        if (ciclosFormativos.length>0){
            for (CicloFormativo c:ciclosFormativos){
                System.out.println(c);
            }
        }else {
            System.out.println("No hay ciclos formativos.");
        }

    }

    private void insertarMatricula(){
        try {
            Matricula matricula = new Matricula(Consola.leerMatricula());
            controlador.insertar(matricula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarMatricula(){
        try {
            Matricula matricula = new Matricula(Consola.leerMatricula());
            controlador.buscar(matricula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void anularMatricula(){
        try {
            Matricula matricula = new Matricula(Consola.leerMatricula());
            controlador.borrar(matricula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarMatriculas() {

        try{
            Matricula[] matriculas = controlador.getMatriculas();

            if (matriculas.length>0){
                for (Matricula m:matriculas){
                    System.out.println(m);
                }
            }else {
                System.out.println("No hay matriculas.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void mostrarMatriculasPorAlumno() {

        try {
            Alumno alumno = Consola.leerAlumno();
            Matricula[] matriculas = controlador.getMatriculas(alumno);

            if (matriculas.length>0){
                for (Matricula m:matriculas){
                    System.out.println(m);
                }
            }else {
                System.out.println("No hay matriculas para el alumno: "+ alumno);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void mostrarMatriculasPorCicloFormativo()  {

        try {
            CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
            Matricula[] matriculas = controlador.getMatriculas(cicloFormativo);

            if (matriculas.length>0){
                for (Matricula m:matriculas){
                    System.out.println(m);
                }
            }else {
                System.out.println("No hay matriculas para el ciclo: "+ cicloFormativo);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void mostrarMatriculasPorCursoAcademico()  {

        try {
            String curso = String.valueOf(Consola.leerCurso());
            Matricula[] matriculas = controlador.getMatriculas(curso);

            if (matriculas.length>0){
                for (Matricula m:matriculas){
                    System.out.println(m);
                }
            }else {
                System.out.println("No hay matriculas para el curso: "+ curso);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}