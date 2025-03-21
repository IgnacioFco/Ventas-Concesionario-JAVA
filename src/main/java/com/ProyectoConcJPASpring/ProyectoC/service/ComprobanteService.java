package com.ProyectoConcJPASpring.ProyectoC.service;

import com.ProyectoConcJPASpring.ProyectoC.DTO.ComprobanteDTO;
import com.ProyectoConcJPASpring.ProyectoC.model.WorldClockResponse;
import com.ProyectoConcJPASpring.ProyectoC.model.*;
import com.ProyectoConcJPASpring.ProyectoC.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ComprobanteService {
    
    private static final Logger logger = Logger.getLogger(ComprobanteService.class.getName());

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Transactional
    public ResponseEntity<ComprobanteDTO> crearComprobanteDTOP(ComprobanteDTO request) {
        ComprobanteDTO response = new ComprobanteDTO();

        try {
            if (request.getCliente() == null || request.getCliente().getId() == null) {
                response.setMensajeError("Se requiere un cliente válido");
                return ResponseEntity.badRequest().body(response);
            }

            Cliente cliente = clienteRepository.findById(request.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + request.getCliente().getId()));

            List<Linea> lineas = request.getLineas();
            if (lineas == null || lineas.isEmpty()) {
                response.setMensajeError("El comprobante debe tener al menos una línea");
                return ResponseEntity.badRequest().body(response);
            }

            double precioTotal = 0;
            int cantidadTotal = 0;

            for (Linea linea : lineas) {
                if (linea.getAuto() == null || linea.getAuto().getId() == null) {
                    response.setMensajeError("Se requiere un auto válido en cada línea");
                    return ResponseEntity.badRequest().body(response);
                }

                Auto auto = autoRepository.findById(linea.getAuto().getId())
                        .orElseThrow(() -> new RuntimeException("Auto no encontrado con ID: " + linea.getAuto().getId()));

                if (linea.getCantidad() <= 0) {
                    response.setMensajeError("La cantidad debe ser mayor que 0");
                    return ResponseEntity.badRequest().body(response);
                }
                
                if (linea.getCantidad() > auto.getStock()) {
                    response.setMensajeError("Stock insuficiente para el auto " + auto.getMarca() + " " + auto.getModelo() + 
                                             ". Disponible: " + auto.getStock() + 
                                             ", Solicitado: " + linea.getCantidad());
                    return ResponseEntity.badRequest().body(response);
                }

                double precioLinea = auto.getPrecio() * linea.getCantidad();
                precioTotal += precioLinea;
                cantidadTotal += linea.getCantidad();
                
                linea.setPrecioUnitario(auto.getPrecio());
                linea.setPrecioTotal(precioLinea);
                linea.setAuto(auto);
                
                auto.setStock(auto.getStock() - linea.getCantidad());
                autoRepository.save(auto);
            }

            LocalDateTime fecha = obtenerFechaDesdeServicioExterno();
            if (fecha == null) {
                fecha = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                logger.info("Usando fecha local como fallback: " + fecha);
            }

            Comprobante comprobante = new Comprobante();
            comprobante.setCliente(cliente);
            comprobante.setLineas(lineas);
            comprobante.setFecha(fecha);
            comprobante.setPrecioTotal(precioTotal);
            comprobante.setCantidadTotal(cantidadTotal);
            comprobante = comprobanteRepository.save(comprobante);

            response.setId(comprobante.getId());
            response.setFecha(comprobante.getFecha());
            response.setPrecioTotal(comprobante.getPrecioTotal());
            response.setCantidadTotal(comprobante.getCantidadTotal());
            response.setCliente(comprobante.getCliente());
            response.setLineas(comprobante.getLineas());

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            logger.severe("Error al crear comprobante: " + e.getMessage());
            response.setMensajeError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    private LocalDateTime obtenerFechaDesdeServicioExterno() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://worldclockapi.com/api/json/utc/now";
            
            logger.info("Obteniendo fecha desde servicio externo: " + url);
            WorldClockResponse worldClockResponse = restTemplate.getForObject(url, WorldClockResponse.class);

            if (worldClockResponse != null && worldClockResponse.getCurrentDateTime() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
                LocalDateTime fecha = LocalDateTime.parse(worldClockResponse.getCurrentDateTime(), formatter);
                logger.info("Fecha obtenida del servicio externo: " + fecha);
                return fecha;
            }
        } catch (Exception e) {
            logger.warning("Error al obtener fecha desde servicio externo: " + e.getMessage());
        }
        logger.warning("No se pudo obtener fecha del servicio externo, se usará fecha local");
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