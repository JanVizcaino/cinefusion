package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.model.Movie;
import com.example_pgr.demo_pgr.model.User;
import com.example_pgr.demo_pgr.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    //OBTENER TODAS LAS PELICULAS
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.listMovies();
        return ResponseEntity.ok(movies);
    }

    //OBTENER UNA PELICULA POR LA ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable int id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    //CREAR UNA NUEVA PELICULA
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        try {
            movieService.saveMovie(movie);
            return new ResponseEntity<>(movie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //ACTUALIZAR UNA PLEICULA EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        try {
            Movie currentMovie = movieService.findById(id);
            if (currentMovie == null) {
                return ResponseEntity.notFound().build();
            }
            currentMovie.setTitle(movie.getTitle());
            currentMovie.setDuration(movie.getDuration());
            currentMovie.setPoster_url(movie.getPoster_url());
            currentMovie.setDirector(movie.getDirector());
            currentMovie.setMain_protagonist(movie.getMain_protagonist());
            currentMovie.setProtagonist(movie.getProtagonist());
            currentMovie.setSynopsis(movie.getSynopsis());
            movieService.saveMovie(currentMovie);
            return ResponseEntity.ok(currentMovie);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    //ELIMINAR UNA PELICULA POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        try {
            movieService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
