package com.delivery.rutas.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seguimiento_gps")
public class SeguimientoGPS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "repartidor_id", nullable = false)
    private Repartidor repartidor;
    
    @Column
    private Double latitud;
    
    @Column
    private Double longitud;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    // Constructores
    public SeguimientoGPS() {
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Repartidor getRepartidor() { return repartidor; }
    public void setRepartidor(Repartidor repartidor) { this.repartidor = repartidor; }
    
    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }
    
    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
