package com.ProyectoConcJPASpring.ProyectoC.DTO;


public class ClienteDTO {
    private String nombre;
    private String apellido;
    private int años;

    public ClienteDTO(String nombre, String apellido, int años) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.años = años;
    }


}
