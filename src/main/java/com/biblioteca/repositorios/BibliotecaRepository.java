package com.biblioteca.repositorios;

import com.biblioteca.entidades.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository  extends JpaRepository  <Biblioteca, Integer>{

}
