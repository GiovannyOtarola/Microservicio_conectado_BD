package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
     @Autowired
     private PeliculaRepository peliculaRepository;
     
     @Test
     public void guardarPeliculaTest(){
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Hola 2");
        pelicula.setAño(2024);
        pelicula.setDirector("Pepito");
        pelicula.setGenero("accion");
        pelicula.setSinopsis("Hola 2 en accion");

        Pelicula resultado = peliculaRepository.save(pelicula);

        assertNotNull(resultado.getId());
        assertEquals("Hola 2", resultado.getTitulo());
        assertEquals(2024, resultado.getAño());
        assertEquals("Pepito", resultado.getDirector());
        assertEquals("accion", resultado.getGenero());
        assertEquals("Hola 2 en accion", resultado.getSinopsis());
     }
}
