package com.alpersurekci.ui.mvc.rest.impl;

import com.alpersurekci.business.dto.TodoDto;
import com.alpersurekci.business.services.IToDoServices;
import com.alpersurekci.data.entity.TodoEntity;
import com.alpersurekci.ui.mvc.rest.IToDoRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ToDoRestImpl implements IToDoRest {

    @Autowired
    IToDoServices services;

    @GetMapping({"/", "/index"})
    public String getRoot() {
        return "index";

    }

    //rest api de save işlemi
    @Override
    @PostMapping("/todo")
    public TodoDto saveToDo(@RequestBody TodoDto todoDto) {
        services.saveToDo(todoDto);
        return todoDto;
    }

    //rest api de listeleme işlemi
    @Override
    @GetMapping("/todo")
    public List<TodoEntity> findAllToDo() {

        return services.findAllToDo();
    }

    //rest api de delete işlemi
    @Override
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteToDo(@PathVariable(name = "id", required = false) Long id) {
        services.deleteToDo(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted ", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //rest api de update işlemi
    @Override
    @PutMapping("/todo/{id}")
    public ResponseEntity<TodoDto> updateToDo(@PathVariable(name = "id") Long id, @RequestBody TodoDto todoDto) {
        services.updateToDo(id, todoDto);
        return ResponseEntity.ok(todoDto);
    }

    //rest api de bütün taskları delete işlemi
    @Override
    @DeleteMapping("/todo/delete/all")
    public ResponseEntity<Map<String, Boolean>> deleteAllToDo() {
        services.deleteAllToDo();
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted All Tasks ", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    //rest api de done task işlemi
    @Override
    @PutMapping("/todo/select/done/{id}")
    public ResponseEntity<Map<String, Boolean>> selectDone(@PathVariable(name = "id") Long id) {
        services.selectDone(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Task is selected", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //rest api de bütün done taskları silme işlemi
    @Override
    @DeleteMapping("/todo/delete/alldone")
    public ResponseEntity<Map<String, Boolean>> deleteAllDoneToDo() {
        services.deleteAllDoneToDo();
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete all done task", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
