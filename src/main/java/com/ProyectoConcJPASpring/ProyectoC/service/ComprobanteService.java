package com.ProyectoConcJPASpring.ProyectoC.service;

import com.ProyectoConcJPASpring.ProyectoC.ComprobanteRequest.ComprobanteRequest;
import com.ProyectoConcJPASpring.ProyectoC.model.*;
import com.ProyectoConcJPASpring.ProyectoC.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    public ResponseEntity<?> crearComprobante(ComprobanteRequest request) {

        Cliente cliente = clienteRepository.findById(request.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<Linea> lineas = request.getLineas();
        double precioTotal = 0;
        int cantidadTotal = 0;

        for (Linea linea : lineas) {

            Auto auto = autoRepository.findById(linea.getAuto().getId())
                    .orElseThrow(() -> new RuntimeException("Auto no encontrado"));

            if (linea.getCantidad() != 1) {
                throw new RuntimeException("La cantidad de autos debe ser 1");
            }

            precioTotal += auto.getPrecio() * linea.getCantidad();
            cantidadTotal += linea.getCantidad();
        }

        LocalDateTime fecha = LocalDateTime.now();

        Comprobante comprobante = new Comprobante();
        comprobante.setCliente(cliente);
        comprobante.setLineas(lineas);
        comprobante.setFecha(fecha);
        comprobante.setPrecioTotal(precioTotal);
        comprobante.setCantidadTotal(cantidadTotal);
        comprobanteRepository.save(comprobante);

        return ResponseEntity.ok(comprobante);
    }

    public Optional<Comprobante> obtenerComprobantePorId(Long id) {
        return comprobanteRepository.findById(id);
    }

    public List<Comprobante> obtenerTodosLosComprobantes() {
        return comprobanteRepository.findAll();
    }

    public void eliminarComprobante(Long id) {
        comprobanteRepository.deleteById(id);
    }
}