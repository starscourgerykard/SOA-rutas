package com.delivery.rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.rutas.models.Ruta;

@Repository
public interface IRutaRepository extends JpaRepository<Ruta, Long>{

}
