package com.biblioteca.controladores;


import com.biblioteca.entidades.Fiesta;
import com.biblioteca.repositorios.FiestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/fiestas")
public class FiestaController {
    @Autowired
    private FiestsRepository fiestsRepository;

    @GetMapping
    public ResponseEntity<Collection<Fiesta>> listarFiesta(){
        return  new ResponseEntity<>(fiestsRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Fiesta> obtenerFiestaPorID(@PathVariable long id){
        Fiesta fiesta = fiestsRepository.findById(id).orElseThrow();

        if (fiesta != null){
            return  new ResponseEntity<>(fiestsRepository.findById(id).orElseThrow() , HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public  ResponseEntity<?> guardarFiesta(@RequestBody Fiesta fiesta){
        return  new ResponseEntity<>(fiestsRepository.save(fiesta), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> eliminarFiesta(@PathVariable long id){
        fiestsRepository.deleteById(id);
        return  new ResponseEntity<Void>(HttpStatus.OK);
    }

}
