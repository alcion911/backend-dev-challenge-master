package com.manager.backend_dev_challenge_master.service;

import com.manager.backend_dev_challenge_master.model.Movie;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class MovieService {
    private RestTemplate restTemplate;
    private final String API_URL = "https://eron-movies.wiremockapi.cloud/api/movies/search?page=";

    public MovieService() {
        this.restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM));
        this.restTemplate.getMessageConverters().add(0, converter);
    }
    public List<Movie> fetchAllMovies() {
        List<Movie> allMovies = new ArrayList<>();
        int page = 1;
        boolean hasMorePages;

        do {
            String url = API_URL + page;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            List<Map<String, Object>> datos = (List<Map<String, Object>>) response.get("data");
            for (Map<String, Object> item : datos) {
                Movie movie = new Movie();
                movie.setTitle((String) item.get("Title"));
                movie.setYear(Integer.parseInt((String)item.get("Year")));
                movie.setDirector((String) item.get("Director"));
                allMovies.add(movie);
            }

            hasMorePages = page < (Integer) response.get("total_pages");
            page++;
        } while (hasMorePages);

        return allMovies;
    }
}