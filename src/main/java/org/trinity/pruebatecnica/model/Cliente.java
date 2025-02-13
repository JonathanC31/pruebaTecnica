package org.trinity.pruebatecnica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El tipo de documento es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name="tipoid")
    private TipoDocumento tipoid;

    @NotNull(message = "El numero de documento es obligatorio")
    @Column(name="numerodoc")
    private String numerodoc;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name="nombre")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(name="apellido")
    private String apellido;

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9\\- ]{7,15}$", message = "Número de teléfono no válido")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres")
    @Column(name="telefono")
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Column(name="email", unique = true)
    private String email;

    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @Column(name="fechaDeNacimiento")
    private LocalDate fechaDeNacimiento;

    @CreationTimestamp
    @Column(updatable = false, name="fechaDeCreacion")
    private LocalDateTime fechaDeCreacion;

    @UpdateTimestamp
    @Column(name="fechaDeModificacion")
    private LocalDateTime fechaDeModificacion;


    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        if (fechaDeNacimiento != null && calcularEdad(fechaDeNacimiento) < 18) {
            throw new IllegalArgumentException("Debe ser mayor de 18 años");
        }
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    private int calcularEdad(LocalDate fechaDeNacimiento) {
        return Period.between(fechaDeNacimiento, LocalDate.now()).getYears();
    }

    public Cliente(Long id, TipoDocumento tipoid, String numerodoc, String apellido, String nombre, String telefono, String email, LocalDate fechaDeNacimiento, LocalDateTime fechaDeCreacion, LocalDateTime fechaDeModificacion) {
        this.id = id;
        this.tipoid = tipoid;
        this.numerodoc = numerodoc;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeModificacion = fechaDeModificacion;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El tipo de documento es obligatorio") TipoDocumento getTipoid() {
        return tipoid;
    }

    public void setTipoid(@NotNull(message = "El tipo de documento es obligatorio") TipoDocumento tipoid) {
        this.tipoid = tipoid;
    }

    public @NotNull(message = "El numero de documento es obligatorio") String getNumerodoc() {
        return numerodoc;
    }

    public void setNumerodoc(@NotNull(message = "El numero de documento es obligatorio") String numerodoc) {
        this.numerodoc = numerodoc;
    }

    public @NotBlank(message = "El nombre no puede estar vacío") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre no puede estar vacío") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El apellido no puede estar vacío") String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank(message = "El apellido no puede estar vacío") String apellido) {
        this.apellido = apellido;
    }

    public @NotBlank(message = "El número de teléfono es obligatorio") @Pattern(regexp = "^\\+?[0-9\\- ]{7,15}$", message = "Número de teléfono no válido") @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "El número de teléfono es obligatorio") @Pattern(regexp = "^\\+?[0-9\\- ]{7,15}$", message = "Número de teléfono no válido") @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres") String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "El email es obligatorio") @Email(message = "El email debe tener un formato válido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "El email es obligatorio") @Email(message = "El email debe tener un formato válido") String email) {
        this.email = email;
    }

    public @Past(message = "La fecha de nacimiento debe estar en el pasado") LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public LocalDateTime getFechaDeModificacion() {
        return fechaDeModificacion;
    }

    public void setFechaDeModificacion(LocalDateTime fechaDeModificacion) {
        this.fechaDeModificacion = fechaDeModificacion;
    }
}
