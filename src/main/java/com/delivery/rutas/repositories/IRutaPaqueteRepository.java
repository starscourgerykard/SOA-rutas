package com.delivery.rutas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.rutas.models.Ruta;
import com.delivery.rutas.models.RutaPaquete;

@Repository
public interface IRutaPaqueteRepository extends JpaRepository<RutaPaquete, Long> {
    List<RutaPaquete> findByRutaId(Ruta ruta);
}
