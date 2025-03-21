package com.ProyectoConcJPASpring.ProyectoC.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.ProyectoConcJPASpring.ProyectoC.DTO.ComprobanteDTO;

@Entity
@Table(name = "comprobante")
@Data
@NoArgsConstructor
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "comprobante_id")
    private List<Linea> lineas;

    private LocalDateTime fecha;
    private double precioTotal;
    private int cantidadTotal;
    
    @Transient
    private String mensajeError;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
    
    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    
    public ComprobanteDTO toResponse() {
        ComprobanteDTO response = new ComprobanteDTO();
        response.setId(this.id);
        response.setFecha(this.fecha);
        response.setPrecioTotal(this.precioTotal);
        response.setCantidadTotal(this.cantidadTotal);
        response.setMensajeError(this.mensajeError);
        return response;
    }
}