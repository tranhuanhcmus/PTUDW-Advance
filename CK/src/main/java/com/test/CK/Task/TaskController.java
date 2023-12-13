package com.test.CK.Task;

import com.test.CK.Task.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = service.getAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Short id) {
        Task task = service.getById(id);
        if (task==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createTask(@RequestBody @Valid Task task) {
        HttpStatus status = service.createTask(task);
        switch (status) {
            case OK -> {
                return new ResponseEntity<>("Create Task Success", status);
            }
            default -> {
                return new ResponseEntity<>("Create Task Failed", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<String> updateTaskById(@PathVariable Short id,@RequestBody @Valid Task task){
        HttpStatus status = service.updateById(id,task);
        switch (status){
            case OK -> {
                return new ResponseEntity<>("Update Task Success",status);
            }
            case NOT_FOUND -> {
                return new ResponseEntity<>("Task is not existed",status);
            }
            default -> {
                return new ResponseEntity<>("Update Task Failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteTaskById(@PathVariable Short id){
        HttpStatus status = service.deleteById(id);
        switch (status){
            case OK -> {
                return new ResponseEntity<>("Delete Task Success",status);
            }
            case NOT_FOUND -> {
                return new ResponseEntity<>("Task is not existed",status);
            }
            default -> {
                return new ResponseEntity<>("Delete Task Failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
