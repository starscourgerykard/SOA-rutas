package com.delivery.rutas.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "rutas")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "repartidor_id", nullable = false)
    private Repartidor repartidor;
    
    @Column(nullable = false)
    private LocalDate fecha;
    
    @Enumerated(EnumType.STRING)
    private EstadoRuta estado = EstadoRuta.PENDIENTE;
    
    @Column
    private Double kmEstimados;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructores
    public Ruta() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Repartidor getRepartidor() { return repartidor; }
    public void setRepartidor(Repartidor repartidor) { this.repartidor = repartidor; }


  public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    
    public EstadoRuta getEstado() { return estado; }
    public void setEstado(EstadoRuta estado) { this.estado = estado; }
    
    public Double getKmEstimados() { return kmEstimados; }
    public void setKmEstimados(Double kmEstimados) { this.kmEstimados = kmEstimados; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

enum EstadoRuta {
    PENDIENTE, EN_PROCESO, COMPLETADA
}
