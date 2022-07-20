package com.alpersurekci.ui.mvc.controller;

import com.alpersurekci.business.services.IToDoServices;
import com.alpersurekci.data.entity.TodoEntity;
import com.alpersurekci.data.repository.ITodoRespository;
import com.alpersurekci.business.dto.TodoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@Log4j2
public class TodoController {

    @Autowired
    IToDoServices service;

    @Autowired
    ITodoRespository repository;

    @GetMapping("/")
    public String getTodoAdd(Model model) {

        model.addAttribute("todo_key", new TodoDto());

        //indexte bütün taskları listeleme
        model.addAttribute("todo_list", service.findAllToDo());

        return "index";
    }

    //yeni task ekleme
    @PostMapping("/save")
    public String postTodoAdd(@Valid @ModelAttribute("todo_key") TodoDto todoDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("postdoAdd isleminde hata");
            return "error";
        }

        service.saveToDo(todoDto);
        return "redirect:/";

    }

    //id'ye göre task silme
    @GetMapping("/delete/do/{id}")
    public String deleteToDo(@PathVariable(name = "id") Long id) {

        service.deleteToDo(id);
        return "redirect:/";
    }

    //id'ye göre task update işlemi
    @PostMapping("/update/do/{id}")
    public String updateToDo(@PathVariable(name = "id") Long id, TodoDto todoDto) {

        service.updateToDo(id, todoDto);
        return "redirect:/";

    }

    //delete all task
    @GetMapping("delete/do/all")
    public String deleteAllToDo() {
        service.deleteAllToDo();
        return "redirect:/";

    }

    //done task
    @GetMapping("/done/{id}")
    public String done(@PathVariable(name = "id") Long id) {
        service.selectDone(id);
        return "redirect:/";
    }

    //all done silme
    @GetMapping("/done/delete/all")
    public String doneDeleteAll() {
        service.deleteAllDoneToDo();
        return "redirect:/";
    }


}
