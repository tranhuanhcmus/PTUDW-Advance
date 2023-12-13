package com.test.CK.Task;

import com.test.CK.Task.Task;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Short id){
        Optional<Task> optionalTask=repository.findById(id);
        if (optionalTask.isEmpty()){
            return  null;
        }
        return optionalTask.get();
    }

    public HttpStatus createTask(Task Task){
        repository.save(Task);
        return  HttpStatus.OK;
    }

    public HttpStatus deleteById(Short id){
        Optional<Task> optionalTask=repository.findById(id);
        if (optionalTask.isEmpty()){
            return  HttpStatus.NOT_FOUND;
        }
        else {
            repository.deleteById(id);
            return  HttpStatus.OK;
        }
    }

    public HttpStatus updateById(Short id,Task updatedTask){
        Optional<Task> optionalTask=repository.findById(id);
        if (optionalTask.isEmpty()){
            return  HttpStatus.NOT_FOUND;
        }
        else {
            Task Task=optionalTask.get();
            Task.setContent(updatedTask.getContent());
            Task.setTitle(updatedTask.getTitle());
            Task.setChecked(updatedTask.isChecked());
            repository.save(Task);
            return  HttpStatus.OK;
        }
    }
}
