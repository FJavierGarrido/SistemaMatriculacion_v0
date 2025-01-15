package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;

import javax.naming.OperationNotSupportedException;

public class MainApp {
    public static final int CAPACIDAD=3;

    private static Alumnos alumnos;
    private static Asignaturas asignaturas;
    private static CiclosFormativos ciclosFormativos;
    private static Matriculas matriculas;
    private Consola consola;


    public static void main(String[] args) {

        alumnos = new Alumnos(CAPACIDAD);
        asignaturas = new Asignaturas(CAPACIDAD);
        ciclosFormativos = new CiclosFormativos(CAPACIDAD);
        matriculas = new Matriculas(CAPACIDAD);

        MainApp mainApp = new MainApp();
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            mainApp.ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }


    private void ejecutarOpcion(Opcion opcion) {
        Opcion[] opciones = Opcion.values();
        int ordinal = opcion.ordinal();

        if (ordinal >= 0 && ordinal < opciones.length) {

            switch (opcion) {
                case INSERTAR_ALUMNO -> insertarAlumno();
                case BUSCAR_ALUMNO -> buscarAlumno();
                case BORRAR_ALUMNO -> borrarAlumno();
                case MOSTRAR_ALUMNOS -> mostrarAlumnos();
                case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
                case BUSCAR_CICLO_FORMATIVO -> buscarCicloFormativo();
                case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
                case MOSTRAR_CICLOS_FORMATIVOS -> mostrarCicloFormativo();
                case INSERTAR_ASIGNATURA -> insertarAsignatura();
                case BUSCAR_ASIGNATURA -> buscarAsignatura();
                case BORRAR_ASIGNATURA -> borrarAsignatura();
                case MOSTRAR_ASIGNATURAS -> mostrarAsignatura();
                case INSERTAR_MATRICULA -> insertarMatricula();
                case ANULAR_MATRICULA -> anularMatricula();
                case MOSTRAR_MATRICULAS -> mostrarMatriculas();
                case MOSTRAR_MATRICULAS_ALUMNO -> mostrarMatriculasPorAlumno();
                case MOSTRAR_MATRICULAS_CICLO_FORMATIVO -> mostrarMatriculasPorCicloFormativo();
                case MOSTRAR_MATRICULAS_CURSO_ACADEMICO -> mostrarMatriculasPorCursoAcademico();
                case SALIR -> System.out.println("¡Hasta pronto!");
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno alumnoEncontrado = alumnos.buscar(alumno);
            if (alumnoEncontrado != null ) {
                System.out.println(alumnoEncontrado);
            } else {
                System.out.println("No se encontró el alumno.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();

            Alumno alumnoEncontrado = alumnos.buscar(alumno);
            if (alumnoEncontrado != null ) {
                alumnos.borrar(alumnoEncontrado);
            } else {
                System.out.println("No se encontró el alumno para borrarlo.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarAlumnos() {
        Alumno[] listaAlumnos = Alumnos.get();
        if (listaAlumnos.length > 0) {
            for (Alumno alumno : listaAlumnos) {
                System.out.println(alumno);
            }
        } else {
            System.out.println("No hay alumnos registrados.");
        }
    }

    private void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloFormativoPorCodigo();
            CicloFormativo cicloEncontrado = ciclosFormativos.buscar(ciclo);
            if (cicloEncontrado != null) {
                System.out.println(cicloEncontrado);
            } else {
                System.out.println("No se encontró el ciclo formativo.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloFormativoPorCodigo();
            CicloFormativo cicloEncontrado = ciclosFormativos.buscar(ciclo);
            if (cicloEncontrado != null) {
                ciclosFormativos.borrar(cicloEncontrado);
                System.out.println("Ciclo formativo borrado correctamente.");
            } else {
                System.out.println("No se encontró el ciclo formativo para borrarlo.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarCicloFormativo() {
        CicloFormativo[] listaciclosFormativos=ciclosFormativos.get();
        if (listaciclosFormativos.length>0) {
            for (CicloFormativo ciclo : ciclosFormativos.get()) {
                System.out.println(ciclo);
            }
        } else {
            System.out.println("No hay ciclos formativos que mostrar.");
        }
    }

    private void insertarAsignatura() {
        try {
            Asignatura asignatura = Consola.leerAsignatura();
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignatura asignaturaEncontrada = asignaturas.buscar(asignatura);
            if (asignaturaEncontrada != null) {
                System.out.println(asignaturaEncontrada);
            } else {
                System.out.println("No se encontró la asignatura.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignatura asignaturaEncontrada = asignaturas.buscar(asignatura);
            if (asignaturaEncontrada != null) {
                asignaturas.borrar(asignaturaEncontrada);
                System.out.println("Asignatura borrada correctamente.");
            } else {
                System.out.println("No se encontró la asignatura para borrarla.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarAsignatura() {
        Asignatura[] listaAsignaturas= asignaturas.get();

        if (listaAsignaturas.length>0) {
            for (Asignatura asignatura : asignaturas.get()) {
                System.out.println(asignatura);
            }
        } else {
            System.out.println("No hay asignaturas que mostrar.");
        }
    }

    private void insertarMatricula() {
        try {
            Matricula matricula = Consola.leerMatricula();
            matriculas.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void anularMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula matriculaEncontrada = matriculas.buscar(matricula);

            if (matriculaEncontrada != null) {
                matriculas.borrar(matriculaEncontrada);
                System.out.println("Matrícula anulada correctamente.");
            } else {
                System.out.println("No se encontró la matrícula para anular.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarMatriculas() {
        try {
            Matricula[] listaMatriculas = matriculas.get();

            if (listaMatriculas.length > 0) {
                for (Matricula matricula : listaMatriculas) {
                    System.out.println(matricula);
                }
            } else {
                System.out.println("No hay matrículas que mostrar.");
            }
        } catch (OperationNotSupportedException e) {
            System.out.println("Error al mostrar las matrículas: " + e.getMessage());
        }
    }

    private void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();

            if (alumno!=null){
                Matricula[] matriculasAlumno = matriculas.get(alumno);

                if (matriculasAlumno.length > 0) {
                    for (Matricula matricula : matriculasAlumno) {
                        System.out.println(matricula);
                    }
                } else {
                    System.out.println("No hay matrículas para este alumno.");
                }
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());

        }
    }

    private void mostrarMatriculasPorCicloFormativo() {
        try {
         //   mostrarCicloFormativo();
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            if (ciclo!=null) {
                Matricula[] matriculasCiclo = matriculas.get(ciclo);

                if (matriculasCiclo.length > 0) {
                    for (Matricula matricula : matriculasCiclo) {
                        System.out.println(matricula);
                    }
                } else {
                    System.out.println("No hay matrículas para este ciclo formativo.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void mostrarMatriculasPorCursoAcademico() {
        try {
            Curso curso = Consola.leerCurso();
            Matricula[] matriculasCurso = matriculas.get(curso.toString());

            if (matriculasCurso.length > 0) {
                for (Matricula matricula : matriculasCurso) {
                    System.out.println(matricula);
                }
            } else {
                System.out.println("No hay matrículas para este curso académico.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
