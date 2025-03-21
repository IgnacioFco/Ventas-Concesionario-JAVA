package com.ProyectoConcJPASpring.ProyectoC.controller;

import com.ProyectoConcJPASpring.ProyectoC.DTO.ComprobanteDTO;
import com.ProyectoConcJPASpring.ProyectoC.service.ComprobanteService;
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
    public ResponseEntity<?> obtenerComprobantePorId(@PathVariable Long id) {
        return ResponseEntity.ok(comprobanteService.obtenerComprobantePorId(id));
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosComprobantes() {
        return ResponseEntity.ok(comprobanteService.obtenerTodosLosComprobantes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarComprobante(@PathVariable Long id) {
        comprobanteService.eliminarComprobante(id);
        return ResponseEntity.ok("Comprobante eliminado correctamente");
    }
}