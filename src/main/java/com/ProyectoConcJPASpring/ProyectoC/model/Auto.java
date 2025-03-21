package com.ProyectoConcJPASpring.ProyectoC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "AUTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auto {

@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    
   private String marca;
   private String modelo;
   private int anio;
   private double precio;
   private int stock;
   

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public String getMarca() {
       return marca;
   }

   public void setMarca(String marca) {
       this.marca = marca;
   }

   public String getModelo() {
       return modelo;
   }

   public void setModelo(String modelo) {
       this.modelo = modelo;
   }

   public int getAnio() {
       return anio;
   }

   public void setAnio(int anio) {
       this.anio = anio;
   }

   public double getPrecio() {
       return precio;
   }

   public void setPrecio(double precio) {
       this.precio = precio;
   }

   public int getStock() {
       return stock;
   }

   public void setStock(int stock) {
       this.stock = stock;
   }

   @ToString.Exclude
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "CONCESIONARIO_ID")
   private Concesionario concesionario;

}
