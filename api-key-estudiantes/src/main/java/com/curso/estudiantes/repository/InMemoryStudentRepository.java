package com.curso.estudiantes.repository;

import com.curso.estudiantes.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> estudiantes = List.of(
        new Student(1L, "Juan Pérez", "juan@correo.com"),
        new Student(2L, "María López", "maria@correo.com"),
        new Student(3L, "Pedro García", "pedro@correo.com")
    );

    @Override
    public List<Student> findAll() {
        return estudiantes;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return estudiantes.stream()
            .filter(student -> student.id().equals(id))
            .findFirst();
    }
}
