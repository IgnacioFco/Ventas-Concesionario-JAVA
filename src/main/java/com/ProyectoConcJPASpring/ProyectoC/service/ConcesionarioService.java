package com.ProyectoConcJPASpring.ProyectoC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoConcJPASpring.ProyectoC.model.Concesionario;
import com.ProyectoConcJPASpring.ProyectoC.repository.ConcesionarioRepository;

@Service
public class ConcesionarioService {

 @Autowired

    private ConcesionarioRepository concesionarioRepository;

    public Concesionario save(Concesionario concesionario){
        return concesionarioRepository.save(concesionario);
    }


}
