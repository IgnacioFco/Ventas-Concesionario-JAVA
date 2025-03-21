package com.ProyectoConcJPASpring.ProyectoC.controller;

import com.ProyectoConcJPASpring.ProyectoC.DTO.AutoDTO;
import com.ProyectoConcJPASpring.ProyectoC.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    public List<AutoDTO> obtenerTodosLosAutos() {
        return autoService.obtenerTodos();
    }

    @PostMapping
    public AutoDTO guardarAuto(@RequestBody AutoDTO autoDTO) {
        return autoService.guardarAuto(autoDTO);
    }
}
