package com.delivery.rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.delivery.rutas.models.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    
}
