package com.biblioteca.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "libros", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @NotNull
    private  String nombre;

    //referencia de la llave foranea   LAZY (carga peresosa)
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name  =  "biblioteca_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  Biblioteca biblioteca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
