package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {

    GDCFGB(""),
    GDCFGM(""),
    GDCFGS("");

    private String cadenaAMostrar;


    private Grado(String cadenaAMostrar){
        this.cadenaAMostrar =cadenaAMostrar;
    }

    public String imprimir(){
        int digito = this.ordinal();
        return digito + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Curso= " + cadenaAMostrar;
    }



}
