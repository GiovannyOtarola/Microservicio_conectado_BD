package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {
     @InjectMocks
     private PeliculaServiceImpl peliculaServicio;
     
     @Mock
     private PeliculaRepository peliculaRepositoryMock;

     @Test
     public void guardarPeliculaTest(){

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Hola");
        pelicula.setAño(2021);
        pelicula.setDirector("Pepito");
        pelicula.setGenero("accion");
        pelicula.setSinopsis("Hola en accion");

        when(peliculaRepositoryMock.save(any())).thenReturn(pelicula);

        Pelicula resultado = peliculaServicio.createPelicula(pelicula);

        assertEquals("Hola", resultado.getTitulo());
        assertEquals(2021, resultado.getAño());
        assertEquals("Pepito", resultado.getDirector());
        assertEquals("accion", resultado.getGenero());
        assertEquals("Hola en accion", resultado.getSinopsis());
     }
}
