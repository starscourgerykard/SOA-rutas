package com.delivery.rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.rutas.models.SeguimientoGPS;

@Repository
public interface ISeguimientoGPSRepository extends JpaRepository<SeguimientoGPS, Long> {
    
}
