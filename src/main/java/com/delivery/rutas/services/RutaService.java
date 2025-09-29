package com.delivery.rutas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.rutas.models.Ruta;
import com.delivery.rutas.repositories.IRutaRepository;

@Service
public class RutaService {
    @Autowired
    private IRutaRepository rutaRepository;
    
    public List<Ruta> obtenerTodasRutas() {
        return rutaRepository.findAll();
    }
    
    public Optional<Ruta> obtenerRutaPorId(Long id) {
        return rutaRepository.findById(id);
    }
    
    public Ruta guardarRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }
    
    public void eliminarRuta(Long id) {
        rutaRepository.deleteById(id);
    }
}
