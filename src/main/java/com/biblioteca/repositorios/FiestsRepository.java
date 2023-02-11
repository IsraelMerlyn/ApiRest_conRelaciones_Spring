package com.biblioteca.repositorios;


import com.biblioteca.entidades.Fiesta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FiestsRepository extends CrudRepository<Fiesta, Long> {
    Collection <Fiesta> findAll();
}
