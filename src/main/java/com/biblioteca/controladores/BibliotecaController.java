package com.biblioteca.controladores;

import com.biblioteca.entidades.Biblioteca;
import com.biblioteca.repositorios.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping ("/api/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @GetMapping
    public  ResponseEntity<Page<Biblioteca>> listarBibliotecas(Pageable pageable){
        return  ResponseEntity.ok(bibliotecaRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity <Biblioteca> guardarBiblioteca(@Valid  @RequestBody  Biblioteca biblioteca){
        Biblioteca bibliotecaGuardada = bibliotecaRepository.save(biblioteca);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bibliotecaGuardada.getId()).toUri();
        return  ResponseEntity.created(ubicacion).body(bibliotecaGuardada);
    }

    @PutMapping ("/{id}")
    public ResponseEntity <Biblioteca> actualizarBiblioteca(@PathVariable Integer id, @Valid  @RequestBody  Biblioteca biblioteca) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);

            if (!bibliotecaOptional.isPresent()) {
                return  ResponseEntity.unprocessableEntity().build();
            }
            biblioteca.setId(bibliotecaOptional.get().getId());
            bibliotecaRepository.save(biblioteca);

            return  ResponseEntity.noContent().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity <Biblioteca> eliminarBiblioteca(@PathVariable Integer id) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);
        if (!bibliotecaOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        bibliotecaRepository.delete(bibliotecaOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Biblioteca> obtenerBibliotecaPorID(@PathVariable Integer id){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);
        if (!bibliotecaOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(bibliotecaOptional.get());
    }
}
