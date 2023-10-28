package com.example.accessData.film;

import jakarta.persistence.Tuple;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<FilmDTO>> getAllFilm(){
        return filmService.getAll();
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> createFilm(@RequestBody @Valid Film film){
        if(!Film.ERatings.contains(film.getRating())){
            return new ResponseEntity<>("Ratings value is not correct", HttpStatus.BAD_REQUEST);
        }
        return filmService.createFilm(film);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable Short id){
        return filmService.getFilmById(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteFilmById(@PathVariable Short id){
        return filmService.deleteFilmById(id);
    }

        @PutMapping(path = "/{id}")
        public ResponseEntity<String> updateFilmById(@PathVariable Short id,@RequestBody @Valid Film film){
            if(!Film.ERatings.contains(film.getRating())){
                return new ResponseEntity<>("Ratings value is not correct", HttpStatus.BAD_REQUEST);
            }
            return filmService.updateFilmById(id,film);
        }

}
