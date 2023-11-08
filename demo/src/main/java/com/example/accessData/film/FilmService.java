package com.example.accessData.film;

import com.example.accessData.language.Language;
import com.example.accessData.language.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;

    public FilmService(FilmRepository filmRepository, LanguageRepository languageRepository) {
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
    }

    public ResponseEntity<List<FilmDTO>> getAll() {
        List<FilmDTO> filmDTOs = filmRepository.findAll().stream()
                .map(FilmDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(filmDTOs, HttpStatus.OK);
    }

    public ResponseEntity<String> createFilm(Film film) {
        String rating=film.getRating();
        if(!Film.ERatings.contains(rating)){
            return new ResponseEntity<>("Rating value is invalid", HttpStatus.BAD_REQUEST);
        }

        Optional<Language> languageOptional = languageRepository.findById(film.getLanguage().getId());

        if (languageOptional.isEmpty()) {
            return new ResponseEntity<>("Language is not exist", HttpStatus.NOT_FOUND);
        }

        film.setLanguage(languageOptional.get());

        filmRepository.save(film);
        return new ResponseEntity<>("Saved Success", HttpStatus.CREATED);
    }

    public ResponseEntity<FilmDTO> getFilmById(Short id) {
        Optional<Film> filmOptional = filmRepository.findById(id);

        if (filmOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FilmDTO filmDTO = new FilmDTO(filmOptional.get());
        return new ResponseEntity<>(filmDTO, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteFilmById(Short id) {

        Optional<Film> filmOptional = filmRepository.findById(id);

        if (filmOptional.isEmpty()) {
            return new ResponseEntity<>("Film is not exist", HttpStatus.NOT_FOUND);
        }

        filmRepository.deleteById(id);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);

    }

    public ResponseEntity<String> updateFilmById(Short id, UpdateFilmDTO filmDto) {

        Optional<Film> filmOptional = filmRepository.findById(id);

        if (filmOptional.isEmpty()) {
            return new ResponseEntity<>("Film is not exist", HttpStatus.NOT_FOUND);
        }

        Byte languageId = filmDto.getLanguageId().byteValue();
        Optional<Language> languageOptional = languageRepository.findById(languageId);

        if (languageOptional.isEmpty()) {
            return new ResponseEntity<>("Language is not exist", HttpStatus.NOT_FOUND);
        }

        Film updateFilm=filmOptional.get();
        updateFilm.setUpdateData(filmDto);
        updateFilm.setLanguage(languageOptional.get());

        filmRepository.save(updateFilm);
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

}
