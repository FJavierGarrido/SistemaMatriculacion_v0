package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {
    PRIMERO("Primero"),
    SEGUNDO("Segundo");

    private String cadenaAmostrar;


    private Curso(String cadenaAmostrar){
        this.cadenaAmostrar=cadenaAmostrar;
    }

    public String imprimir(){
        int orden = this.ordinal();
        return orden + ".-" + cadenaAmostrar;
    }

    @Override
    public String toString() {
        return "Curso= " + cadenaAmostrar ;
    }
}
