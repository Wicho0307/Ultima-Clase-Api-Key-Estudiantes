package com.curso.estudiantes.repository;

import com.curso.estudiantes.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findById(Long id);
}
