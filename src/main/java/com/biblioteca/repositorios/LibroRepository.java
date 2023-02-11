package com.biblioteca.repositorios;

import com.biblioteca.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository <Libro, Integer> {
}
