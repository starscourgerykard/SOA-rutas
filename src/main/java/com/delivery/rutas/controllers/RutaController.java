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

import com.delivery.rutas.models.Ruta;
import com.delivery.rutas.services.RutaService;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {
    @Autowired
    private RutaService rutaService;
    
    @GetMapping
    public List<Ruta> obtenerTodasRutas() {
        return rutaService.obtenerTodasRutas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ruta> obtenerRutaPorId(@PathVariable Long id) {
        Optional<Ruta> ruta = rutaService.obtenerRutaPorId(id);
        return ruta.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Ruta crearRuta(@RequestBody Ruta ruta) {
        return rutaService.guardarRuta(ruta);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ruta> actualizarRuta(
            @PathVariable Long id, @RequestBody Ruta rutaDetalles) {
        Optional<Ruta> rutaOptional = rutaService.obtenerRutaPorId(id);
        
        if (rutaOptional.isPresent()) {
            Ruta ruta = rutaOptional.get();
            ruta.setRepartidor(rutaDetalles.getRepartidor());
            ruta.setFecha(rutaDetalles.getFecha());
            ruta.setEstado(rutaDetalles.getEstado());
            ruta.setKmEstimados(rutaDetalles.getKmEstimados());
            
            Ruta rutaActualizado = rutaService.guardarRuta(ruta);
            return ResponseEntity.ok(rutaActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRuta(@PathVariable Long id) {
        Optional<Ruta> ruta = rutaService.obtenerRutaPorId(id);
        
        if (ruta.isPresent()) {
            rutaService.eliminarRuta(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
