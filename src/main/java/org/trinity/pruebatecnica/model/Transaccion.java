package org.trinity.pruebatecnica.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransaccion tipo;

    @Column(nullable = false)
    private Double monto;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id")
    private Producto cuentaOrigen; // Puede ser nulo en caso de consignación

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id")
    private Producto cuentaDestino; // Puede ser nulo en caso de retiro

    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha;

    @PrePersist
    private void prePersist() {
        this.fecha = LocalDateTime.now();
    }

    public Transaccion(Long id, TipoTransaccion tipo, Double monto, Producto cuentaOrigen, Producto cuentaDestino, LocalDateTime fecha) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.fecha = fecha;
    }

    public Transaccion() {
    }
}

