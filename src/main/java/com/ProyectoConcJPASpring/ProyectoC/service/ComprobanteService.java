package com.ProyectoConcJPASpring.ProyectoC.service;

import com.ProyectoConcJPASpring.ProyectoC.ComprobanteRequest.ComprobanteRequest;
import com.ProyectoConcJPASpring.ProyectoC.DTO.ComprobanteDTO;
import com.ProyectoConcJPASpring.ProyectoC.model.*;
import com.ProyectoConcJPASpring.ProyectoC.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public ResponseEntity<ComprobanteDTO> crearComprobante(ComprobanteRequest request) {
        ComprobanteDTO response = new ComprobanteDTO();

        try {
            Cliente cliente = clienteRepository.findById(request.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            List<Linea> lineas = request.getLineas();
            double precioTotal = 0;
            int cantidadTotal = 0;

            for (Linea linea : lineas) {
                Auto auto = autoRepository.findById(linea.getAuto().getId())
                        .orElseThrow(() -> new RuntimeException("Auto no encontrado"));

                if (linea.getCantidad() != 1) {
                    response.setMensajeError("La cantidad de autos debe ser 1");
                    return ResponseEntity.badRequest().body(response);
                }

                precioTotal += auto.getPrecio() * linea.getCantidad();
                cantidadTotal += linea.getCantidad();
            }

            LocalDateTime fecha = obtenerFechaDesdeServicioExterno();
            if (fecha == null) {
                fecha = LocalDateTime.now();
            }

            Comprobante comprobante = new Comprobante();
            comprobante.setCliente(cliente);
            comprobante.setLineas(lineas);
            comprobante.setFecha(fecha);
            comprobante.setPrecioTotal(precioTotal);
            comprobante.setCantidadTotal(cantidadTotal);
            comprobanteRepository.save(comprobante);

            response.setId(comprobante.getId());
            response.setFecha(comprobante.getFecha());
            response.setPrecioTotal(comprobante.getPrecioTotal());
            response.setCantidadTotal(comprobante.getCantidadTotal());

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            response.setMensajeError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    private LocalDateTime obtenerFechaDesdeServicioExterno() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://worldclockapi.com/api/json/utc/now";
            WorldClockResponse worldClockResponse = restTemplate.getForObject(url, WorldClockResponse.class);

            if (worldClockResponse != null && worldClockResponse.getCurrentDateTime() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
                return LocalDateTime.parse(worldClockResponse.getCurrentDateTime(), formatter);
            }
        } catch (Exception e) {
        }
        return null;
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