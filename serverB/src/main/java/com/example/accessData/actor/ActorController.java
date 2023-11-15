package com.example.accessData.actor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/actors")
@Tag(name = "Actor", description = "the actor API")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @Operation(summary = "Add new actor", description = "Add new actor" )
    @PostMapping(path = "/")
    public ResponseEntity<String> addNewActor(@RequestBody @Valid ActorDTO actorDTO) {
        return actorService.createActor(actorDTO);
    }

    @Operation(summary = "Get all actors", description = "Get all actors in database" )
    @GetMapping(path = "/")
    public ResponseEntity<List<ActorDTO>> getAllActors() {
        return actorService.getAll();
    }

    @Operation(summary = "Get actor by id", description = "Get actor in database by actor_id" )
    @GetMapping(path = "/{id}")
    public ResponseEntity<ActorDTO> getActor(@Parameter(description = "actor_id field of actor you want to get") @PathVariable Short id) {
        return actorService.getActorById(id);
    }

    @Operation(summary = "Delete actor by id", description = "Delete actor in database by actor_id" )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteActor(@Parameter(description = "actor_id field of actor you want to delete") @PathVariable Short id) {
        return actorService.deleteActorById(id);
    }

    @Operation(summary = "Update actor by id", description = "Update actor in database by actor_id" )
    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateActor(@Parameter(description = "actor_id field of actor you want to update") @PathVariable Short id, @RequestBody @Valid ActorDTO actorDTO) {

        return actorService.updateActor(id, actorDTO);
    }

}
