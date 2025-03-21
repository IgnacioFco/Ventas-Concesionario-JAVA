package com.ProyectoConcJPASpring.ProyectoC.mapper;

import com.ProyectoConcJPASpring.ProyectoC.DTO.AutoDTO;
import com.ProyectoConcJPASpring.ProyectoC.model.Auto;
import org.springframework.stereotype.Component;

@Component
public class AutoMapper {

    public AutoDTO toDTO(Auto auto) {
        return new AutoDTO(auto.getMarca(), auto.getModelo(), auto.getPrecio());
    }

    public Auto toEntity(AutoDTO autoDTO) {
        return new Auto(null, autoDTO.getMarca(), autoDTO.getModelo(), 0, autoDTO.getPrecio(), null);
    }
}
