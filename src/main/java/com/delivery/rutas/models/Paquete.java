package com.delivery.rutas.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "paquetes")
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Column
    private Double pesoKg;
    
    @Enumerated(EnumType.STRING)
    private EstadoPaquete estado = EstadoPaquete.PENDIENTE;
    
    @Column(name = "fecha_entrega_estimada")
    private LocalDate fechaEntregaEstimada;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(length = 100)
    private String destino;

    // Constructores
    public Paquete() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Double getPesoKg() { return pesoKg; }
    public void setPesoKg(Double pesoKg) { this.pesoKg = pesoKg; }
    
    public EstadoPaquete getEstado() { return estado; }
    public void setEstado(EstadoPaquete estado) { this.estado = estado; }
    
    public LocalDate getFechaEntregaEstimada() { return fechaEntregaEstimada; }
    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) { this.fechaEntregaEstimada = fechaEntregaEstimada; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}

enum EstadoPaquete {
    PENDIENTE, EN_TRANSITO, ENTREGADO
}
