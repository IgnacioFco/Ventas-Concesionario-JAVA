package com.ProyectoConcJPASpring.ProyectoC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ProyectoConcJPASpring.ProyectoC.model.Auto;

public interface AutoRepository extends JpaRepository<Auto, Long> {


    @Query("SELECT a FROM Auto a WHERE a.marca = ?1")
    Auto getByMarca(@Param("marca") String id);


}
