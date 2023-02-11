package com.biblioteca.controladores;

import com.biblioteca.entidades.Biblioteca;
import com.biblioteca.entidades.Libro;
import com.biblioteca.repositorios.BibliotecaRepository;
import com.biblioteca.repositorios.LibroRepository;
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
@RequestMapping ("/api/libros")
public class LibrosController {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @GetMapping
    public  ResponseEntity<Page<Libro>> listarLibro(Pageable pageable){
        return  ResponseEntity.ok(libroRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity <Libro> guardarLibro(@Valid  @RequestBody Libro libro){
      Optional <Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());
        if (!bibliotecaOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        libro.setBiblioteca(bibliotecaOptional.get());
        Libro libroGuardado = libroRepository.save(libro);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(libroGuardado.getId()).toUri();

        return  ResponseEntity.created(ubicacion).body(libroGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity <Libro> actualizarLibro(@PathVariable Integer id,@Valid  @RequestBody Libro libro){
        Optional <Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());
        if (!bibliotecaOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if (!libroOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        libro.setBiblioteca(bibliotecaOptional.get());
       libro.setId(libroOptional.get().getId());
        libroRepository.save(libro);
        return  ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Libro> eliminarLibro(@PathVariable Integer id){
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if (!libroOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        libroRepository.delete(libroOptional.get());
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Libro> obtenerLibroPorID(@PathVariable Integer id){
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if (!libroOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        return  ResponseEntity.ok(libroOptional.get());
    }


}
