package com.example.accessData.language;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public ResponseEntity<List<Language>> getAll(){
        return new ResponseEntity<>(languageRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> createLanguage(Language language){

        Language newLanguage=new Language(language);

        languageRepository.save(newLanguage);

        return new ResponseEntity<>("Created Success",HttpStatus.OK);
    }

    public ResponseEntity<Optional<Language>> getById( Byte id){
        return new ResponseEntity<>(languageRepository.findById(id),HttpStatus.OK);
    }

    public ResponseEntity<String> deleteById(Byte id){
        Optional<Language> languageOptional=languageRepository.findById(id);
        if(languageOptional.isEmpty()){
            return new ResponseEntity<>("Language is not exist",HttpStatus.NOT_FOUND);
        }
        languageRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Success",HttpStatus.OK);
    }
    public ResponseEntity<String> updateById(Byte id,Language language){
        Optional<Language> languageOptional=languageRepository.findById(id);
        if(languageOptional.isEmpty()){
            return new ResponseEntity<>("Language is not exist",HttpStatus.NOT_FOUND);
        }

        Language updateLanguage=languageOptional.get();
        updateLanguage.setFilms(language.getFilms());
        updateLanguage.setId(language.getId());
        updateLanguage.setName(language.getName());

        languageRepository.save(updateLanguage);

        return new ResponseEntity<>("Updated Success",HttpStatus.OK);
    }
}
