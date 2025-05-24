package com.asistencia.modelo;

public class Materia {
    private String nombreMateria;
    private String catedra;
    private String horario;

    public Materia(String nombreMateria, String catedra, String horario) {
        this.nombreMateria = nombreMateria;
        this.catedra = catedra;
        this.horario = horario;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getCatedra() {
        return catedra;
    }

    public void setCatedra(String catedra) {
        this.catedra = catedra;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return
                "Materia='" + nombreMateria + '\'' +
                        ", catedra='" + catedra + '\'' +
                        ", horario='" + horario + '\'' +
                        "\n===================";
    }
}
