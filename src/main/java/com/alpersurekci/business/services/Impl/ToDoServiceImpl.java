package com.alpersurekci.business.services.Impl;

import com.alpersurekci.business.dto.TodoDto;
import com.alpersurekci.business.services.IToDoServices;
import com.alpersurekci.data.entity.TodoEntity;
import com.alpersurekci.data.repository.ITodoRespository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ToDoServiceImpl implements IToDoServices {

    @Autowired
    ITodoRespository repository;


    @Autowired
    ModelMapper modelMapper;


    @Override
    public TodoDto entityToDto(TodoEntity todoEntity) {
        TodoDto todoDto = modelMapper.map(todoEntity, TodoDto.class);
        return todoDto;
    }

    @Override
    public TodoEntity dtoToEntity(TodoDto todoDto) {
        TodoEntity todoEntity = modelMapper.map(todoDto, TodoEntity.class);
        return todoEntity;
    }

    @Override
    public List<TodoEntity> findAllToDo() {

        List<TodoEntity> list = repository.findAll();

        return list;
    }

    //task ekleme
    @Override
    public void saveToDo(TodoDto todoDto) {
        TodoEntity todoEntity = dtoToEntity(todoDto);
        repository.save(todoEntity);

    }

    //task silme
    @Override
    public ResponseEntity<TodoDto> deleteToDo(Long id) {
        Optional<TodoEntity> todoEntity = repository.findById(id);
        TodoDto todoDto = new TodoDto();
        if (todoEntity.isPresent()) {

            repository.deleteById(id);
            todoDto = entityToDto(todoEntity.get());
        } else {
            log.info("can't delete");
        }
        return ResponseEntity.ok(todoDto);

    }
    //task update
    @Override
    public ResponseEntity<TodoDto> updateToDo(Long id, TodoDto todoDto) {
        Optional<TodoEntity> todoEntity = repository.findById(id);
        if (todoEntity.isPresent()) {

            todoEntity.get().setDoName(todoDto.getDoName());
            repository.save(todoEntity.get());
            log.info("guncellendi");

        } else {
            log.info("updateToDo da hata");
        }
        return ResponseEntity.ok(todoDto);
    }
    //bütün taskları silme
    @Override
    public void deleteAllToDo() {
        repository.deleteAll();

    }
    //done task
    @Override
    public void selectDone(Long id) {
        Optional<TodoEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            entity.get().setCompleted(!entity.get().isCompleted());
            repository.save(entity.get());
        } else {
            log.info("selectDone Method error");
        }
    }
    //bütün done taskları silme
    @Override
    public void deleteAllDoneToDo() {

        List<TodoEntity> allEntities = repository.findAllByCompletedEquals(true);

        for (TodoEntity entity : allEntities) {
            repository.deleteById(entity.getId());
        }

    }
}
