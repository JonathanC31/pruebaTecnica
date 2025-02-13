package org.trinity.pruebatecnica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Random;



@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo de cuenta es obligatorio")
    @Column(name = "tipo_cuenta")
    private TipoCuenta tipoCuenta;

    @Column(name = "numero_cuenta", unique = true, length = 10, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCuenta estado;

    @NotNull(message = "El saldo no puede ser nulo")
    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "exento_gmf")
    private Boolean exentoGMF;

    @CreationTimestamp
    @Column(updatable = false, name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @PrePersist
    private void prePersist() {
        this.numeroCuenta = generarNumeroCuenta();
        if (this.tipoCuenta == TipoCuenta.AHORROS) {
            this.estado = EstadoCuenta.ACTIVA; // Cuentas de ahorro activas por defecto
            if (this.saldo == null) {
                this.saldo = 0.0; // Saldo inicial
            }
        }
    }

    private String generarNumeroCuenta() {
        String prefix = (this.tipoCuenta == TipoCuenta.AHORROS) ? "53" : "33";
        Random random = new Random();
        String numeroAleatorio = String.format("%08d", random.nextInt(100000000)); // 8 dígitos aleatorios
        return prefix + numeroAleatorio;
    }

    public void setSaldo(Double saldo) {
        if (this.tipoCuenta == TipoCuenta.AHORROS && saldo < 0) {
            throw new IllegalArgumentException("El saldo de una cuenta de ahorros no puede ser menor a 0");
        }
        this.saldo = saldo;
    }

    public void setEstado(EstadoCuenta estado) {
        if (estado == EstadoCuenta.CANCELADA && this.saldo > 0) {
            throw new IllegalArgumentException("Solo se pueden cancelar cuentas con saldo 0");
        }
        this.estado = estado;
    }

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El tipo de cuenta es obligatorio") TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(@NotNull(message = "El tipo de cuenta es obligatorio") TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public @NotNull(message = "El saldo no puede ser nulo") Double getSaldo() {
        return saldo;
    }

    public Boolean getExentoGMF() {
        return exentoGMF;
    }

    public void setExentoGMF(Boolean exentoGMF) {
        this.exentoGMF = exentoGMF;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
