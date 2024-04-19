package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception{
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Hola 2");
        pelicula1.setAño(2024);
        pelicula1.setDirector("Pepito");
        pelicula1.setGenero("accion");
        pelicula1.setSinopsis("Hola 2 en accion");
        pelicula1.setId(1L);
        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Hola");
        pelicula2.setAño(2021);
        pelicula2.setDirector("Pepito");
        pelicula2.setGenero("accion");
        pelicula2.setSinopsis("Hola en accion");
        pelicula2.setId(2L);
        List<Pelicula> peliculas = List.of(pelicula1, pelicula2);

        List<EntityModel<Pelicula>> peliculasResources = peliculas.stream()
         .map(pelicula -> EntityModel.of(pelicula))
         .collect(Collectors.toList());

        when(peliculaServiceMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.peliculas.length()").value(2))
                .andExpect(jsonPath("$._embedded.peliculas[0].titulo").value("Hola 2"))
                .andExpect(jsonPath("$._embedded.peliculas[0].año").value(2024))
                .andExpect(jsonPath("$._embedded.peliculas[0].director").value("Pepito"))
                .andExpect(jsonPath("$._embedded.peliculas[0].genero").value("accion"))
                .andExpect(jsonPath("$._embedded.peliculas[0].sinopsis").value("Hola 2 en accion"))
                .andExpect(jsonPath("$._embedded.peliculas[0].id").value(1))
                .andExpect(jsonPath("$._embedded.peliculas[0]._links.self.href").value("http://localhost:8080/peliculas/1"))
                .andExpect(jsonPath("$._embedded.peliculas[1].titulo").value("Hola"))
                .andExpect(jsonPath("$._embedded.peliculas[1].año").value(2021))
                .andExpect(jsonPath("$._embedded.peliculas[1].director").value("Pepito"))
                .andExpect(jsonPath("$._embedded.peliculas[1].genero").value("accion"))
                .andExpect(jsonPath("$._embedded.peliculas[1].sinopsis").value("Hola en accion"))
                .andExpect(jsonPath("$._embedded.peliculas[1].id").value(2))
                .andExpect(jsonPath("$._embedded.peliculas[1]._links.self.href").value("http://localhost:8080/peliculas/2"));

    }
    
}
