package com.ProyectoConcJPASpring.ProyectoC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProyectoConcJPASpring.ProyectoC.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}