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
        private Long id;  // Campo "id"
    
        private String nombre;
        private String apellido;
        private LocalDate fechaNacimiento;
    
        public Long getId() {  // Método getId()
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getNombre() {
            return nombre;
        }
    
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    
        public String getApellido() {
            return apellido;
        }
    
        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
    
        public LocalDate getFechaNacimiento() {
            return fechaNacimiento;
        }
    
        public void setFechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }
    }
