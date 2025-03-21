package com.ProyectoConcJPASpring.ProyectoC.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoDTO {
   private Long id;
   private String marca;
   private String modelo;
   private int anio;
   private double precio;
   private int stock;
   
   public AutoDTO(String marca, String modelo, double precio) {
       this.marca = marca;
       this.modelo = modelo;
       this.precio = precio;
   }
   
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
}