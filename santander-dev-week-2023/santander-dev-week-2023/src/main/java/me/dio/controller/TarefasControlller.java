package me.dio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import me.dio.model.Tarefa;
import me.dio.repository.TarefasRepository;

public class TarefasControlller {
    @Autowired
    private TarefasRepository tarefasRepository;

    //Para a listagem de todas as tarefas
    @GetMapping
    public List<Tarefa> getAllTasks() {
        return tarefasRepository.findAll();
    }

    // Cria uma nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> createTask(@RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = tarefasRepository.save(tarefa);
        return new ResponseEntity<>(tarefaSalva,HttpStatus.CREATED);
    }

    //Retorna uma tarefa específica
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTask(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefasRepository.findById(id);
        if (tarefa.isPresent()) {
            return new ResponseEntity<>(tarefa.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Atualiza uma tarefa existente
    @PutMapping("/{id}") 
    public ResponseEntity<Tarefa> updateTask(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        Optional<Tarefa> existingTask = tarefasRepository.findById(id);
        if (existingTask.isPresent()) {
            tarefa.setId(id);
            Tarefa tarefaAtulizada = tarefasRepository.save(tarefa);
            return new ResponseEntity<>(tarefaAtulizada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Deleta uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if(tarefasRepository.existsById(id)) {
            tarefasRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
