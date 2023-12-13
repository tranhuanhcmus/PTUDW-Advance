package com.test.CK.Actor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorService {

    private final ActorRepository repository;

    public ActorService(ActorRepository repository) {
        this.repository = repository;
    }

    public List<Actor> getAll(){
        List<Actor> result=repository.findAll();
       return result;
    }

    public Actor getById(Short id){
        Optional<Actor> optionalActor=repository.findById(id);
        if (optionalActor.isEmpty()){
            return null;
        }
        return optionalActor.get();
    }

    public HttpStatus deleteById(Short id){
        Optional<Actor> optionalActor=repository.findById(id);
        if (optionalActor.isEmpty()){
            return HttpStatus.NOT_FOUND;
        }
        else {
            repository.deleteById(id);
            return HttpStatus.OK;
        }
    }

    public HttpStatus createActor(Actor actor){
        repository.save(actor);
        return HttpStatus.OK;
    }

    public HttpStatus updateById(Short id,Actor updatedActor){
        Optional<Actor> optionalActor=repository.findById(id);
        if(optionalActor.isEmpty()){
            return  HttpStatus.NOT_FOUND;
        }
        else {
            Actor actor=optionalActor.get();
            actor.setFirstName(updatedActor.getFirstName());
            actor.setLastName(updatedActor.getLastName());
            repository.save(actor);
            return HttpStatus.OK;
        }
    }
}
