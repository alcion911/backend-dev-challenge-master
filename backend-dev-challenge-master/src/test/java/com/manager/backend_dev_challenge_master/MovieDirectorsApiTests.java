package com.manager.backend_dev_challenge_master;

import com.manager.backend_dev_challenge_master.controller.MovieController;
import com.manager.backend_dev_challenge_master.model.Movie;
import com.manager.backend_dev_challenge_master.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
public class MovieDirectorsApiTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void testGetDirectors() throws Exception {
        // Simulamos el comportamiento del servicio
        when(movieService.fetchAllMovies()).thenReturn(List.of(
                createMovie("Movie1", 2011, "Director1"),
                createMovie("Movie4", 2014, "Director2"),
                createMovie("Movie2", 2012, "Director1"),
                createMovie("Movie3", 2013, "Director2"),
                createMovie("Movie5", 2013, "Director3")
        ));

        // Realizamos la prueba del endpoint
        mockMvc.perform(get("/api/directors?threshold=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.directors[0]").value("Director1"))
                .andExpect(jsonPath("$.directors[1]").value("Director2"));
    }

    // Método auxiliar para crear películas
    private Movie createMovie(String title, int year, String director) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(year);
        movie.setDirector(director);
        return movie;
    }
}