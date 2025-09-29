package com.delivery.rutas.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rutas_paquetes")
public class RutaPaquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;
    
    @ManyToOne
    @JoinColumn(name = "paquete_id", nullable = false)
    private Paquete paquete;
    
    @Column(name = "orden_entrega")
    private Integer ordenEntrega;
    
    private Boolean entregado = false;
    
    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Ruta getRuta() { return ruta; }
    public void setRuta(Ruta ruta) { this.ruta = ruta; }
    
    public Paquete getPaquete() { return paquete; }
    public void setPaquete(Paquete paquete) { this.paquete = paquete; }
    
    public Integer getOrdenEntrega() { return ordenEntrega; }
    public void setOrdenEntrega(Integer ordenEntrega) { this.ordenEntrega = ordenEntrega; }
    
    public Boolean getEntregado() { return entregado; }
    public void setEntregado(Boolean entregado) { this.entregado = entregado; }
    
    public LocalDateTime getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }
}
