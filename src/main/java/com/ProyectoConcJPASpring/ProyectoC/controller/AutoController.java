package com.ProyectoConcJPASpring.ProyectoC.controller;

import com.ProyectoConcJPASpring.ProyectoC.DTO.AutoDTO;
import com.ProyectoConcJPASpring.ProyectoC.service.AutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    @Operation(summary = "Obtenemos todos los registros de los autos en la DB H2", description = "Obtenemos registros almacenados manualmente en la DB desde un endpoint externo")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Succesful Operation", content = @Content(schema = @Schema(implementation = AutoDTO.class))),
    @ApiResponse(
        responseCode = "404", description = "Autos not found", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    
        public List<AutoDTO> obtenerTodosLosAutos() {
        return autoService.obtenerTodos();
    }

    @PostMapping
    public AutoDTO guardarAuto(@RequestBody AutoDTO autoDTO) {
        return autoService.guardarAuto(autoDTO);
    }
}
