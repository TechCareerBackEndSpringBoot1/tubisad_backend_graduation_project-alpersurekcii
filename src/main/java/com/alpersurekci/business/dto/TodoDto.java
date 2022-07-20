package com.alpersurekci.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TodoDto {

    private Long ToDoId;

    @NotEmpty(message = "To do cannot be empty!!")
    private String doName;

    //task completed kontrolü için
    private boolean completed;

}
