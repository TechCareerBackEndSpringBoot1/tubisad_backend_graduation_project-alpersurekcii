package com.alpersurekci.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "todo_table")
public class TodoEntity extends BaseEntity {

    @Column(name = "doName")
    private String doName;

    @Column(name = "completed", columnDefinition = "boolean default false")
    private boolean completed;
}
