package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaServiceImpl;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaServiceImpl peliculaServiceMock;

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
        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);

      

        when(peliculaServiceMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo", Matchers.is("Hola 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].año", Matchers.is(2024)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].director", Matchers.is("Pepito")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genero", Matchers.is("accion")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sinopsis", Matchers.is("Hola 2 en accion")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].titulo", Matchers.is("Hola")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].año", Matchers.is(2021)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].director", Matchers.is("Pepito")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].genero", Matchers.is("accion")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sinopsis", Matchers.is("Hola en accion")));

    }
    
}
