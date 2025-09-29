package com.delivery.rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.rutas.models.Repartidor;

@Repository
public interface IRepartidorRepository extends JpaRepository<Repartidor, Long> {

}
