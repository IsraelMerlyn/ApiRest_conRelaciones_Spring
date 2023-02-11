package com.biblioteca.entidades;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "habilidad")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habilidad_id")
    private  long id;

    @Enumerated(EnumType.STRING)
    private  Nivel nivel;
    private  String nombre;
    @ManyToOne

    @JoinColumn(name = "persona_id")
    @JsonBackReference
    private  Persona persona;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
