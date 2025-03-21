package com.ProyectoConcJPASpring.ProyectoC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoConcJPASpring.ProyectoC.DTO.ClienteDTO;
import com.ProyectoConcJPASpring.ProyectoC.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteServicio;

    @GetMapping("/{id}")
    public ClienteDTO obtenerCliente(@PathVariable Long id) {
        return clienteServicio.obtenerClientePorId(id);
    }
}
