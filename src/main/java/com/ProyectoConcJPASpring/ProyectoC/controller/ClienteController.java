package com.ProyectoConcJPASpring.ProyectoC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoConcJPASpring.ProyectoC.DTO.ClienteDTO;
import com.ProyectoConcJPASpring.ProyectoC.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteServicio;

    @GetMapping("/{id}")
     @Operation(summary = "Obtenemos todos los registros de los clientes en la DB H2", description = "Obtenemos registros almacenados manualmente en la DB desde un endpoint externo")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Succesful Operation", content = @Content(schema = @Schema(implementation = ClienteDTO.class))),
    @ApiResponse(
        responseCode = "404", description = "ClientesID not found", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        ClienteDTO cliente = clienteServicio.obtenerClientePorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<Iterable<ClienteDTO>> listarTodosLosClientes() {
        return ResponseEntity.ok(clienteServicio.obtenerTodosLosClientes());
    }

}