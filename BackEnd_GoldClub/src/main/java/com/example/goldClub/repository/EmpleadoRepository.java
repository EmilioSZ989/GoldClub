package com.example.goldClub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goldClub.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByCodigoEmpleado(int codigoEmpleado);
}
