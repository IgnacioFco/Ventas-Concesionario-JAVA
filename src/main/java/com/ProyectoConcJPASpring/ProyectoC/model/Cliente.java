package com.ProyectoConcJPASpring.ProyectoC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    public LocalDate getFechaNacimiento() {
            return fechaNacimiento;
    }
    public String getNombre() {
            return nombre;
      
    }
    public String getApellido() {
            return apellido;
       
    }

}
