package com.delivery.rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.rutas.models.Paquete;

@Repository
public interface IPaqueteRepository extends JpaRepository<Paquete, Long> {
   
}
