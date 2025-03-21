package com.ProyectoConcJPASpring.ProyectoC.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoConcJPASpring.ProyectoC.DTO.ClienteDTO;
import com.ProyectoConcJPASpring.ProyectoC.model.Cliente;
import com.ProyectoConcJPASpring.ProyectoC.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        int edad = Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears();

        return new ClienteDTO(cliente.getNombre(), cliente.getApellido(), edad);
    }
}
