package com.biblioteca.controladores;

import com.biblioteca.entidades.Fiesta;
import com.biblioteca.entidades.Persona;
import com.biblioteca.repositorios.FiestsRepository;
import com.biblioteca.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.schema.Collections;

import java.util.Collection;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private FiestsRepository fiestsRepository;

    @GetMapping
    public ResponseEntity<Collection<Persona>> listarPersonas(){
        return  new ResponseEntity<>(personaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Persona> obtenerPersonaPorID(@PathVariable long id){
        Persona persona = personaRepository.findById(id).orElseThrow();

        if (persona != null){
            return  new ResponseEntity<>(personaRepository.findById(id).orElseThrow() , HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public  ResponseEntity<?> guardarPersona(@RequestBody Persona persona){
        return  new ResponseEntity<>(personaRepository.save(persona), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> eliminarPersona(@PathVariable long id){
        personaRepository.deleteById(id);
        return  new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{id}/fiesta")
    public  ResponseEntity<Collection<Fiesta>> listarFiestasPersonas(@PathVariable long id){
        Persona persona = personaRepository.findById(id).orElseThrow();
        if (persona != null){
            return  new ResponseEntity<>(persona.getFiestas(), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
