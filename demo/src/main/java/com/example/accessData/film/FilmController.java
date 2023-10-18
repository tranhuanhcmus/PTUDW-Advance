package com.example.accessData.film;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/films")
@Tag(name = "Film", description = "the film API")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @Operation(summary = "Get all film", description = "Get all film available" )
    @ApiResponses(value = { @ApiResponse(responseCode = "200",description = "successful operation")})
    @GetMapping(path = "")
    public ResponseEntity<List<FilmDTO>> getAllFilm(){
        return filmService.getAll();
    }

    @Operation(summary = "Create new film", description = "Create new film" )
    @PostMapping(path = "")
    public ResponseEntity<String> createFilm(@RequestBody @Valid Film film){
        return filmService.createFilm(film);
    }

    @Operation(summary = "Get detail film", description = "Get detail film by film_id" )
    @GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "200", description = "OK",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FilmDTO.class))})
    })
    public ResponseEntity<FilmDTO> getFilmById( @Parameter(description = "film_id field of film you want to update", required = true) @PathVariable Short id){
        return filmService.getFilmById(id);
    }

    @Operation(summary = "Delete film by id", description = "Delete film by film_id" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "Object not exists"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteFilmById(@Parameter(description = "film_id field of film you want to delete") @PathVariable Short id){
        return filmService.deleteFilmById(id);
    }

    @Operation(summary = "Update film by film_id", description = "Update film attributes", tags = { "film-controller" } )
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK",content = @Content),  })
    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateFilmById(
            @Parameter(description = "film_id field of film you want to update")
            @PathVariable Short id,
            @RequestBody @Valid UpdateFilmDTO film){
        return filmService.updateFilmById(id,film);
    }

}
