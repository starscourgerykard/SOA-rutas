package com.delivery.rutas.controllers;

import com.delivery.rutas.models.Paquete;
import com.delivery.rutas.services.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {
    @Autowired
    private PaqueteService paqueteService;
    
    @GetMapping
    public List<Paquete> obtenerTodosPaquetes() {
        return paqueteService.obtenerTodosPaquetes();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Paquete> obtenerPaquetePorId(@PathVariable Long id) {
        Optional<Paquete> paquete = paqueteService.obtenerPaquetePorId(id);
        return paquete.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Paquete crearPaquete(@RequestBody Paquete paquete) {
        return paqueteService.guardarPaquete(paquete);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Paquete> actualizarPaquete(
            @PathVariable Long id, @RequestBody Paquete paqueteDetalles) {
        Optional<Paquete> paqueteOptional = paqueteService.obtenerPaquetePorId(id);
        
        if (paqueteOptional.isPresent()) {
            Paquete paquete = paqueteOptional.get();
            paquete.setCliente(paqueteDetalles.getCliente());
            paquete.setDescripcion(paqueteDetalles.getDescripcion());
            paquete.setPesoKg(paqueteDetalles.getPesoKg());
            paquete.setEstado(paqueteDetalles.getEstado());
            paquete.setFechaEntregaEstimada(paqueteDetalles.getFechaEntregaEstimada());
            
            Paquete paqueteActualizado = paqueteService.guardarPaquete(paquete);
            return ResponseEntity.ok(paqueteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaquete(@PathVariable Long id) {
        Optional<Paquete> paquete = paqueteService.obtenerPaquetePorId(id);
        
        if (paquete.isPresent()) {
            paqueteService.eliminarPaquete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
