package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
        controlador.comenzar();
    }

}
