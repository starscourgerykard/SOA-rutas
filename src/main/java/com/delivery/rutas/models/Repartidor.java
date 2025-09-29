package com.delivery.rutas.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "repartidores")
public class Repartidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(length = 15)
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVehiculo vehiculo;
    
    @Column(name = "placa_vehiculo", length = 20)
    private String placaVehiculo;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructores
    public Repartidor() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public TipoVehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(TipoVehiculo vehiculo) { this.vehiculo = vehiculo; }
    
    public String getPlacaVehiculo() { return placaVehiculo; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }
    
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

enum TipoVehiculo {
    MOTO, AUTO, CAMION
}
