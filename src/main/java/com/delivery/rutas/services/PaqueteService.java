package com.delivery.rutas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.rutas.models.Paquete;
import com.delivery.rutas.repositories.IPaqueteRepository;

@Service
public class PaqueteService {
    @Autowired
    private IPaqueteRepository paqueteRepository;
    
    public List<Paquete> obtenerTodosPaquetes() {
        return paqueteRepository.findAll();
    }
    
    public Optional<Paquete> obtenerPaquetePorId(Long id) {
        return paqueteRepository.findById(id);
    }
    
    public Paquete guardarPaquete(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }
    
    public void eliminarPaquete(Long id) {
        paqueteRepository.deleteById(id);
    }
}
