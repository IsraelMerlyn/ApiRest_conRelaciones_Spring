package com.biblioteca.repositorios;

import com.biblioteca.entidades.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {
    Collection <Persona> findAll();
}
