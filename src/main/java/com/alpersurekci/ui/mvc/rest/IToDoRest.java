package com.alpersurekci.ui.mvc.rest;


import com.alpersurekci.business.dto.TodoDto;
import com.alpersurekci.data.entity.TodoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IToDoRest {

    TodoDto saveToDo(TodoDto todoDto);

    List<TodoEntity> findAllToDo();

    ResponseEntity<Map<String, Boolean>> deleteToDo(Long id);

    ResponseEntity<TodoDto> updateToDo(Long id, TodoDto todoDto);

    ResponseEntity<Map<String, Boolean>> deleteAllToDo();

    ResponseEntity<Map<String, Boolean>> selectDone(Long id);

    ResponseEntity<Map<String, Boolean>> deleteAllDoneToDo();
}
