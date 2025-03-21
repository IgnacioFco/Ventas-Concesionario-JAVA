package com.ProyectoConcJPASpring.ProyectoC.ComprobanteRequest;

import com.ProyectoConcJPASpring.ProyectoC.model.Cliente;
import com.ProyectoConcJPASpring.ProyectoC.model.Linea;

import java.util.List;

public class ComprobanteRequest {
    private Cliente cliente;
    private List<Linea> lineas;

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
}
