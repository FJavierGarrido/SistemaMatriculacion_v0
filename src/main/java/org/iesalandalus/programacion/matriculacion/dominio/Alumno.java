package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {
    private static final String ER_TELEFONO= "[6|7|8|9][0-9]{8}";
    private static final String ER_CORREO="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String ER_DNI= "([0-9]{8})([a-zA-Z])";
    public static final String FORMATO_FECHA="\\d{2}/\\d{2}/\\d{4}";
    private static final String ER_NIA="";
    public static final int MIN_EDAD_ALUMNADO = 16;

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;

    private String formateaNombre(String nombre){

        if (nombre==null) {
            throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()){
            throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
        }

        //elimina espacios en blanco del principio y final y divide palabras
        nombre = nombre.trim();
        String[] palabras =nombre.split("\\s");
        StringBuilder nombreFormateado = new StringBuilder();

        //recorre cada palabra y pone mayúscula la primera letra, minúsculas las demás y un espacio al final
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                palabra = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();

                // Añade la palabra formateada al StringBuilder
                nombreFormateado.append(palabra).append(" ");
            }
        }
        return nombreFormateado.toString().trim();
    }

    private boolean comprobarLetraDni(String dni) {

        if (dni == null) {
            throw new NullPointerException("ERROR: El DNI no puede ser nulo");
        }

        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: Formato de DNI no válido");
        }


        // Expresión regular para validar un DNI español (8 dígitos y una letra al final)
        Pattern pattern = Pattern.compile(ER_DNI);
        Matcher matcher = pattern.matcher(dni);

        // Verificar si el formato del DNI es correcto
        if (matcher.matches()) {
            // Obtener el número y la letra del DNI usando grupos
            String numeroDni = matcher.group(1);
            String letraDni = matcher.group(2).toUpperCase(); // Convertir a mayúsculas

            // Calcular la letra correspondiente al número del DNI directamente
            String letrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";

            // Verificar si la letra pasada es válida
            char letraEsperada = letrasValidas.charAt(Integer.parseInt(numeroDni) % 23);
            if (letraDni.charAt(0) == letraEsperada) {
                return true;
            } else {
                throw new IllegalArgumentException("ERROR: La letra del dni no es correcta.");
            }
        } else {
            // El formato del DNI no es válido
            throw new IllegalArgumentException("Formato de DNI no válido");
        }
    }

}
