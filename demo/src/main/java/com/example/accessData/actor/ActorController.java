package com.example.accessData.actor;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewActor(@RequestBody @Valid ActorDTO form) {
        return actorService.addNewActor(form);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Actor>> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Actor>> getActor(@PathVariable Short id) {
        return actorService.getActorById(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Short id) {
        return actorService.deleteActorById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateActor(@PathVariable Short id, @RequestBody @Valid ActorDTO form) {

        return actorService.updateActor(id, form);
    }

}
