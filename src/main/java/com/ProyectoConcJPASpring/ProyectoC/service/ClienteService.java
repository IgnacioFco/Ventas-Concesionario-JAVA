package com.ProyectoConcJPASpring.ProyectoC.service;

import java.util.List;
import java.util.stream.Collectors;

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
        return clienteRepository.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private ClienteDTO convertirADTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        // Mapear otros campos según sea necesario
        return dto;
    }
}