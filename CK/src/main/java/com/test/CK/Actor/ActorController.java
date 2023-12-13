package com.test.CK.Actor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/api/v1/actors")
public class ActorController {
    private final ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Actor>> getAll() {
        List<Actor> actors = service.getAll();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getById(@PathVariable Short id) {
        Actor actor = service.getById(id);
        if (actor==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createActor(@RequestBody @Valid Actor actor) {
        HttpStatus status = service.createActor(actor);
        switch (status) {
            case OK -> {
                return new ResponseEntity<>("Create Actor Success", status);
            }
            default -> {
                return new ResponseEntity<>("Create Actor Failed", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<String> updateActorById(@PathVariable Short id,@RequestBody @Valid Actor actor){
        HttpStatus status = service.updateById(id,actor);
        switch (status){
            case OK -> {
                return new ResponseEntity<>("Update Actor Success",status);
            }
            case NOT_FOUND -> {
                return new ResponseEntity<>("Actor is not existed",status);
            }
            default -> {
                return new ResponseEntity<>("Update Actor Failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteActorById(@PathVariable Short id){
        HttpStatus status = service.deleteById(id);
        switch (status){
            case OK -> {
                return new ResponseEntity<>("Delete Actor Success",status);
            }
            case NOT_FOUND -> {
                return new ResponseEntity<>("Actor is not existed",status);
            }
            default -> {
                return new ResponseEntity<>("Delete Actor Failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
