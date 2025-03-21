package com.ProyectoConcJPASpring.ProyectoC.service;

import com.ProyectoConcJPASpring.ProyectoC.DTO.AutoDTO;
import com.ProyectoConcJPASpring.ProyectoC.mapper.AutoMapper;
import com.ProyectoConcJPASpring.ProyectoC.model.Auto;
import com.ProyectoConcJPASpring.ProyectoC.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private AutoMapper autoMapper;

    public List<AutoDTO> obtenerTodos() {
        return autoRepository.findAll().stream()
                .map(autoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AutoDTO guardarAuto(AutoDTO autoDTO) {
        Auto auto = autoMapper.toEntity(autoDTO);
        Auto autoGuardado = autoRepository.save(auto);
        return autoMapper.toDTO(autoGuardado);
    }

    public Auto save(Auto auto) {
        return autoRepository.save(auto);
}

}
