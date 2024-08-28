package com.example.goldClub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goldClub.models.Empleado;
import com.example.goldClub.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Listar todos los empleados
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    // Guardar un empleado
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Buscar un empleado por su código
    public Optional<Empleado> buscarPorCodigoEmpleado(int codigoEmpleado) {
        return empleadoRepository.findByCodigoEmpleado(codigoEmpleado);
    }

    // Eliminar un empleado por su código
    public void eliminarEmpleado(int codigoEmpleado) {
        empleadoRepository.deleteById(codigoEmpleado);
    }
}
