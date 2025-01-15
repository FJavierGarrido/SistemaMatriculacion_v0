package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class Consola {
    // Constructor privado para evitar instanciación
    private Consola() {
    }

    public static void mostrarMenu() {
        System.out.println("\n----------  MENÚ  ----------");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    public static Opcion elegirOpcion() {
        int opcion = 0;
        try {
            do {
                System.out.println("\nIntroduce el número de opción: ");
                opcion = Entrada.entero();
            } while (opcion < 0 || opcion >= Opcion.values().length);
        } catch (Exception e) {
            System.out.println("No ha introducido un número correcto del menú: " + e.getMessage());
        }

        // Devuelve la instancia de Opcion correspondiente al valor entero
        return Opcion.values()[opcion];
    }

    public static Alumno leerAlumno() throws Exception{
        System.out.println("\n----  Ingresar datos del Alumno  ----");

        System.out.println("Ingrese el nombre: ");
        String nombre = Entrada.cadena();

        System.out.println("Ingrese el DNI: ");
        String dni = Entrada.cadena();

        System.out.println("Ingrese el email: ");
        String correo = Entrada.cadena();

        System.out.println("Ingrese el tel�fono: ");
        String telefono = Entrada.cadena();

        System.out.println("Ingrese la fecha de nacimiento: (dd/mm/yyyy)");
        String fechaNacimiento = Entrada.cadena();


        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaNacimiento, formato);


        return new Alumno(nombre, dni, correo, telefono, fecha);

    }

    public static Alumno getAlumnoPorDni() {

        System.out.println("\nIngrese el DNI: ");
        String dni = Entrada.cadena();

        // Recorre la colección de huéspedes para buscar el DNI
        for (Alumno alumno : Alumnos.get()) {
            if (alumno.getDni().equals(dni)) {
                // Entra y devuelve los datos, si encuentra el alumno
                System.out.println("Alumno encontrado:");
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Dni: " + alumno.getDni());
                System.out.println("Correo: " + alumno.getCorreo());
                System.out.println("Teléfono: " + alumno.getTelefono());
                System.out.println("Fecha de Nacimiento: " + alumno.getFechaNacimiento());
                return alumno;
            }
        }

        // Si no se encuentra un huésped con el DNI proporcionado
        System.out.println("No se encontró ningún huésped con el DNI: " + dni);
        return null;
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        do {
            System.out.println(mensaje + ", formato (dd/MM/yyyy): ");
            String fechaStr = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaStr);
            } catch (Exception e) {
                System.out.println("ERROR: Formato de fecha incorrecto. Utiliza el formato dd/MM/yyyy");
            }
        } while (fecha == null);

        return fecha;
    }

    public static Grado leerGrado(){
        System.out.println("\n--- Grados disponibles ---");
        Grado[] grados = Grado.values();
        for (int i = 0; i < grados.length; i++) {
            System.out.println(i + ". " + grados[i].toString());
        }

        int opcion;
        do {
            System.out.print("Elige el número del grado: ");
            try {
                opcion = Entrada.entero();
                if (opcion < 0 || opcion >= grados.length) {
                    System.out.println("ERROR: Opción no válida.");
                } else {
                    return grados[opcion];
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Introduce un número válido.");
            }
        } while (true);
    }


    public static CicloFormativo leerCicloFormativo(){

        System.out.println("\n--- Introduce los datos del ciclo formativo ---");
        boolean valido=false;
        int codigo;
        do {

            System.out.println("Código del ciclo formativo (Número de cuatro dígitos):");
            codigo = Entrada.entero();

            //System.out.println("ERROR: El código debe ser un número entero positivo.");
            if (codigo >999 && codigo<=9999) {
                valido=true;
            }

        } while (!valido);


        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();
        String familiaProfesional = especialidadProfesorado.toString();

        Grado grado = leerGrado();
        String nombre = grado.toString();

        int horas;
        do {
            System.out.print("\nNúmero de horas del ciclo formativo: ");
            try {
                horas = Integer.parseInt(Entrada.cadena());
                if (horas <= 0) {
                    System.out.println("ERROR: El número de horas debe ser un número entero positivo.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Introduce un número válido para el número de horas.");
                horas=0;
            }
        } while (horas <= 0);


        try {
            return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR al crear el ciclo formativo: " + e.getMessage());
        }
        return null;
    }

    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos) {
        if (ciclosFormativos == null) {
            throw new IllegalArgumentException("Los ciclos formativos no pueden ser nulos.");
        }

        System.out.println("\n--- Ciclos Formativos Registrados ---");

        // Obtenemos la copia profunda del array de ciclos formativos
        CicloFormativo[] ciclos = ciclosFormativos.get();

        if (ciclos.length > 0) {
            for (CicloFormativo ciclo : ciclos) {
                if (ciclo != null) {
                    System.out.println(ciclo.toString());
                }
            }
        } else {
            System.out.println("No hay ciclos formativos registrados en el sistema.");
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() throws Exception {
        System.out.println("\nIntroduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();

        // Creamos un ciclo formativo ficticio con datos válidos
        try {
            return new CicloFormativo(codigo, "Desarrollo de Aplicaciones Web", Grado.GDCFGB,"DAW", 2000);
        } catch (IllegalArgumentException e) {
            throw new Exception("Error al crear el ciclo formativo: " + e.getMessage());
        }
    }

    public static Curso leerCurso() {
        System.out.println("\n----  Lista de cursos existentes   ----");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }

        System.out.println("Introduce el número correspondiente al curso:");
        int opcion;
        do {
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Curso.values().length);
        return Curso.values()[opcion];

    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("\n---  Lista de especialidades existentes  ---");

        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad.imprimir());
        }

        System.out.println("Introduce el número correspondiente a la especialidad:");
        int opcion;
        do {
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= EspecialidadProfesorado.values().length);
        return EspecialidadProfesorado.values()[opcion];
    }

    public static Asignatura leerAsignatura() {
        System.out.println("Introduce el código de la asignatura:");
        String codigo = Entrada.cadena();
        System.out.println("Introduce el nombre de la asignatura:");
        String nombre = Entrada.cadena();
        System.out.println("Introduce las horas anuales de la asignatura:");
        int horasAnuales = Entrada.entero();
        System.out.println("Introduce las horas de desdoble de la asignatura:");
        int horasDesdoble = Entrada.entero();
        System.out.println("Selecciona el curso de la asignatura:");
        Curso curso = leerCurso();
        System.out.println("Selecciona la especialidad del profesorado de la asignatura:");
        EspecialidadProfesorado especialidad = leerEspecialidadProfesorado();
        System.out.println("Introduce el ciclo formativo de la asignatura:");
        CicloFormativo cicloFormativo = leerCicloFormativo();
        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidad, cicloFormativo);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        System.out.println("\nIntroduce el código de la asignatura: ");
        String codigo = Entrada.cadena();

        //Datos ficticios de cicloFormativo
        CicloFormativo cicloFormativo = new CicloFormativo(1234, "Familia Ficticia", Grado.GDCFGB, "Ciclo Ficticio", 2000);

        //Datos ficticios de todo excepto código
        return new Asignatura(codigo, "Asignatura Ficticia", 100, Curso.PRIMERO, 10, EspecialidadProfesorado.INFORMATICA, cicloFormativo);


        }

    private static boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura) {
        if (asignaturasMatricula == null) {
            throw new NullPointerException("ERROR: Asignatura matricula es nula.");
        }

        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede comprobar la matrícula de una asignatura nula.");
        }

        for (Asignatura asignaturaActual : asignaturasMatricula) {
            if (asignaturaActual != null && asignaturaActual.getCodigo().equals(asignatura.getCodigo())) {
                return true;
            }
        }

        return false;
    }

    public static Matricula leerMatricula() throws Exception {
        System.out.println("\n----  Introduce los datos de la matrícula  ----");

        System.out.println("Identificador de la matrícula:");
        int idMatricula = Entrada.entero();

        System.out.println("Curso académico (formato XX-XX):");
        String cursoAcademico = Entrada.cadena();

        System.out.println("Fecha de matriculación (formato dd-MM-yyyy):");
        String fechaStr = Entrada.cadena();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA);
        LocalDate fechaMatriculacion = LocalDate.parse(fechaStr, formatter);

        System.out.println("\n--- Datos del alumno ---");
        Alumno alumno = leerAlumno();

        System.out.println("\nIntroduce las asignaturas (máximo " + Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA + "):");
        Asignatura[] asignaturas = new Asignatura[Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA];
        int numAsignaturas = 0;

        while (numAsignaturas < Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            System.out.println("¿Desea añadir una asignatura? (S/N):");
            String respuesta = Entrada.cadena();
            if (respuesta.equalsIgnoreCase("N")) {
                break;
            }

            Asignatura nuevaAsignatura = leerAsignatura();
            if (!asignaturaYaMatriculada(asignaturas, nuevaAsignatura)) {
                asignaturas[numAsignaturas++] = nuevaAsignatura;
            } else {
                System.out.println("ERROR: La asignatura ya está incluida en la matrícula.");
            }
        }

        // Crear array final solo con las asignaturas añadidas
        Asignatura[] asignaturasFinales = new Asignatura[numAsignaturas];
        System.arraycopy(asignaturas, 0, asignaturasFinales, 0, numAsignaturas);

        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturasFinales);
    }

    public static Matricula getMatriculaPorIdentificador() throws Exception {
        System.out.println("\nIntroduce el identificador de la matrícula:");
        int idMatricula = Entrada.entero();

        // Creamos datos ficticios que cumplan las restricciones
        LocalDate fechaMatriculacion = LocalDate.now().minusDays(1); // Un día antes para cumplir restricciones
        Alumno alumno = new Alumno("Alumno Ficticio", "11111111A", "alumno@ejemplo.com", "666666666", LocalDate.now().minusYears(20));

        // Crear una asignatura ficticia que cumpla las restricciones
        CicloFormativo cicloFicticio = new CicloFormativo(1234, "Familia Ficticia", Grado.GDCFGB, "Ciclo Ficticio", 2000);
        Asignatura asignaturaFicticia = new Asignatura("1234", "Asignatura Ficticia", 100, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cicloFicticio);

        Asignatura[] asignaturas = new Asignatura[]{asignaturaFicticia};

        return new Matricula(idMatricula, "23-24", fechaMatriculacion, alumno, asignaturas);
    }

}
