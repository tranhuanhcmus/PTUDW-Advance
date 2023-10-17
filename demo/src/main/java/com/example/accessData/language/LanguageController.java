package com.example.accessData.language;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Language>> getAll(){
        return languageService.getAll();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Language>> getById(@PathVariable Byte id){
        return languageService.getById(id);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Byte id){
        return languageService.deleteById(id);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateById(@PathVariable Byte id,@RequestBody @Valid Language language){
        return languageService.updateById(id,language);
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> createLanguage(@RequestBody @Valid Language language){
        return languageService.createLanguage(language);
    }

}
