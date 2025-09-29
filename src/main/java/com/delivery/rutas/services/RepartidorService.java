package com.delivery.rutas.services;

import com.delivery.rutas.models.Repartidor;
import com.delivery.rutas.repositories.IRepartidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepartidorService {
    @Autowired
    private IRepartidorRepository repartidorRepository;
    
    public List<Repartidor> obtenerTodosRepartidores() {
        return repartidorRepository.findAll();
    }
    
    public Optional<Repartidor> obtenerRepartidorPorId(Long id) {
        return repartidorRepository.findById(id);
    }
    
    public Repartidor guardarRepartidor(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }
    
    public void eliminarRepartidor(Long id) {
        repartidorRepository.deleteById(id);
    }

  public List<Repartidor> obtenerRepartidoresActivos() {
    return repartidorRepository.findAll().stream()
      .filter(Repartidor::getActivo) // Asumiendo que getActivo() devuelve un Boolean
      .toList();
  }

}
