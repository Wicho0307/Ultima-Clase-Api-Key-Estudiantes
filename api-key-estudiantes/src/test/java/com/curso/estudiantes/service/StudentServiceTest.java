package com.curso.estudiantes.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.curso.estudiantes.repository.StudentRepository;
import com.curso.estudiantes.model.Student;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldReturnAllStudents() {
        List<Student> students = List.of(
            new Student(1L, "John Doe", "john.doe@example.com"),
            new Student(2L, "Jane Smith", "jane.smith@example.com"));
            when(studentRepository.findAll()).thenReturn(students);
            List<Student> result = studentService.findAll();
            assertEquals(2,result.size());
            assertEquals("John Doe", result.get(0).nombre());
            verify(studentRepository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoStudentsFound() {
        List<Student> students = List.of();
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> result = studentService.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(studentRepository).findAll();
    }
}