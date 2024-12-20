package com.manager.backend_dev_challenge_master.model;

import java.util.List;
import lombok.Data;

@Data
public class Movie {
    private String title;
    private int year;
    private String rated;
    private String released;
    private int runtime;
    private String genre;
    private String director;
    private List<String> writer;
    private List<String> actors;

    public void setMainData(String title, int year, String director){
        this.title = title;
        this.year = year;
        this.director = director;
    }
}

/**
 * Title: title of the movie
 * - Year: year the movie was released
 * - Rated: movie rating
 * - Released: movie release date
 * - Runtime: movie duration time in minutes
 * - Genre: move genre
 * - Director: movie director
 * - Writer: movie writers
 * - Actors: movie actors
 */