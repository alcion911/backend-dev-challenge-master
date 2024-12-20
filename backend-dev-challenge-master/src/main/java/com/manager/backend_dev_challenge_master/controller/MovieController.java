package com.manager.backend_dev_challenge_master.controller;

import com.manager.backend_dev_challenge_master.model.Movie;
import com.manager.backend_dev_challenge_master.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/api/directors")
    public ResponseEntity<Map<String, List<String>>> getDirectors(@RequestParam("threshold") int threshold) {
        List<Movie> movies = movieService.fetchAllMovies();

        Map<String, Long> directorCount = movies.stream()
                .filter(movie -> movie.getYear() > 2010 && movie.getDirector() != null)
                .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));

        List<String> result = directorCount.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());

        Map<String, List<String>> response = new HashMap<>();
        response.put("directors", result);

        return ResponseEntity.ok(response);
    }
}