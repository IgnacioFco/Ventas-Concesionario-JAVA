package com.ProyectoConcJPASpring.ProyectoC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoConcJPASpring.ProyectoC.model.Auto;
import com.ProyectoConcJPASpring.ProyectoC.repository.AutoRepository;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    public Auto getByMarca(String marca){
        return autoRepository.getByMarca(marca);
    }

    public void save(Auto auto){
        autoRepository.save(auto);
    }

}
