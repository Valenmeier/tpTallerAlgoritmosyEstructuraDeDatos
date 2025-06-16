package com.asistencia.modelo;

public class Operacion {
    private String tipo;
    private Persona estadoAnterior;
    private Persona estadoNuevo;
    private int indice;

    public Operacion(String tipo, Persona estadoAnterior, Persona estadoNuevo, int indice) {
        this.tipo = tipo;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.indice = indice;
    }

    public String getTipo() {
        return tipo;
    }

    public Persona getEstadoAnterior() {
        return estadoAnterior;
    }

    public Persona getEstadoNuevo() {
        return estadoNuevo;
    }

    public int getIndice() {
        return indice;
    }
}
