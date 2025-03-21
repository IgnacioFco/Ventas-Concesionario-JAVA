package com.ProyectoConcJPASpring.ProyectoC.mapper;

import com.ProyectoConcJPASpring.ProyectoC.DTO.AutoDTO;
import com.ProyectoConcJPASpring.ProyectoC.model.Auto;
import org.springframework.stereotype.Component;

@Component
public class AutoMapper {

    public AutoDTO toDTO(Auto auto) {
        AutoDTO dto = new AutoDTO(
            auto.getMarca(), 
            auto.getModelo(), 
            auto.getPrecio()
        );
        
        dto.setStock(auto.getStock());
        
        dto.setAnio(auto.getAnio());
        
        dto.setId(auto.getId());
        
        return dto;
    }

    public Auto toEntity(AutoDTO autoDTO) {
        Auto auto = new Auto();
        
        auto.setId(autoDTO.getId());
        auto.setMarca(autoDTO.getMarca());
        auto.setModelo(autoDTO.getModelo());
        auto.setAnio(autoDTO.getAnio());
        auto.setPrecio(autoDTO.getPrecio());
        auto.setStock(autoDTO.getStock());
        
        return auto;
    }
    
    public void updateEntityFromDTO(AutoDTO autoDTO, Auto auto) {
        if (autoDTO.getMarca() != null) {
            auto.setMarca(autoDTO.getMarca());
        }
        
        if (autoDTO.getModelo() != null) {
            auto.setModelo(autoDTO.getModelo());
        }
        
        if (autoDTO.getAnio() > 0) {
            auto.setAnio(autoDTO.getAnio());
        }
        
        if (autoDTO.getPrecio() > 0) {
            auto.setPrecio(autoDTO.getPrecio());
        }
        
        if (autoDTO.getStock() >= 0) {
            auto.setStock(autoDTO.getStock());
        }
    }
}