package com.delivery.rutas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.rutas.models.Repartidor;
import com.delivery.rutas.services.RepartidorService;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorController {
    @Autowired
    private RepartidorService repartidorService;
    
    @GetMapping
    public List<Repartidor> obtenerTodosRepartidores() {
        return repartidorService.obtenerTodosRepartidores();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Repartidor> obtenerRepartidorPorId(@PathVariable Long id) {
        Optional<Repartidor> repartidor = repartidorService.obtenerRepartidorPorId(id);
        return repartidor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Repartidor crearRepartidor(@RequestBody Repartidor repartidor) {
        return repartidorService.guardarRepartidor(repartidor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Repartidor> actualizarRepartidor(
            @PathVariable Long id, @RequestBody Repartidor repartidorDetalles) {
        Optional<Repartidor> repartidorOptional = repartidorService.obtenerRepartidorPorId(id);
        
        if (repartidorOptional.isPresent()) {
            Repartidor repartidor = repartidorOptional.get();
            repartidor.setNombre(repartidorDetalles.getNombre());
            repartidor.setTelefono(repartidorDetalles.getTelefono());
            repartidor.setVehiculo(repartidorDetalles.getVehiculo());
            repartidor.setPlacaVehiculo(repartidorDetalles.getPlacaVehiculo());
            repartidor.setActivo(repartidorDetalles.getActivo());
            
            Repartidor repartidorActualizado = repartidorService.guardarRepartidor(repartidor);
            return ResponseEntity.ok(repartidorActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRepartidor(@PathVariable Long id) {
        Optional<Repartidor> repartidor = repartidorService.obtenerRepartidorPorId(id);
        
        if (repartidor.isPresent()) {
            repartidorService.eliminarRepartidor(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
