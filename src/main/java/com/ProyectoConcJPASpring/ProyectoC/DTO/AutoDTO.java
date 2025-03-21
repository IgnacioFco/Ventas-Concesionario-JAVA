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


   private String marca; 

   private String modelo;

   private double precio;

}