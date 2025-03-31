package com.ProyectoConcJPASpring.ProyectoC.controller;

import com.ProyectoConcJPASpring.ProyectoC.DTO.ComprobanteDTO;
import com.ProyectoConcJPASpring.ProyectoC.service.ComprobanteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comprobantes")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @PostMapping
    public ResponseEntity<ComprobanteDTO> crearComprobante(@RequestBody ComprobanteDTO request) {
        return comprobanteService.crearComprobanteDTOP(request);
    }

    @GetMapping("/{id}")
     @Operation(summary = "Obtenemos todos los registros de los comprobantes en la DB H2", description = "Obtenemos registros almacenados manualmente en la DB desde un endpoint externo por su ID")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Succesful Operation", content = @Content(schema = @Schema(implementation = ComprobanteDTO.class))),
    @ApiResponse(
        responseCode = "404", description = "Comprobantes not found", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<?> obtenerComprobantePorId(@PathVariable Long id) {
        return ResponseEntity.ok(comprobanteService.obtenerComprobantePorId(id));
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosComprobantes() {
        return ResponseEntity.ok(comprobanteService.obtenerTodosLosComprobantes());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un comprobante de la DB", description = "Borrar un comprobante por su ID")
    @ApiResponse(responseCode = "204", description = "Comprobante deleted")
    @ApiResponse(responseCode = "404", description = "Comprobante no encotrado")
    public ResponseEntity<?> eliminarComprobante(@PathVariable Long id) {
        comprobanteService.eliminarComprobante(id);
        return ResponseEntity.ok("Comprobante eliminado correctamente");
    }
}