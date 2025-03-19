package com.ProyectoConcJPASpring.ProyectoC.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CONCESIONARIO")
public class Concesionario {

    @Id
   @GeneratedValue
   @Column(name = "CONCESIONARIO_ID")
   private Long id;

   @Column(name = "NOMBRE")
   private String nombre;

   @OneToMany(mappedBy = "concesionario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Auto> autos;

}