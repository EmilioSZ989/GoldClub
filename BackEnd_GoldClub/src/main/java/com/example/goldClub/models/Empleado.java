package com.example.goldClub.models;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "empleados")
public class Empleado {
    
    @Id
    @Column(name = "codigo_empleado")
    private int codigoEmpleado;

    @Column(nullable = false)
    private String nombre;

    // Relación con Usuario
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    // Getters y Setters
    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
