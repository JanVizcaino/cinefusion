package com.example_pgr.demo_pgr.services;

import com.example_pgr.demo_pgr.model.Movie;
import com.example_pgr.demo_pgr.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> listMovies() {
        return movieRepository.findAll();
    }
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    public boolean deleteById(Integer id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
