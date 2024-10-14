package com.secondChance.Models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Many-to-Many relationship with TaskEntity
    @ManyToMany(mappedBy = "tags")
    private Set<TaskEntity> tasks = new HashSet<>(); // Many tags can belong to many tasks

    // Constructors, getters, setters, etc.
    public TagEntity() {}

    public TagEntity(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<TaskEntity> getTasks() { return tasks; }
    public void setTasks(Set<TaskEntity> tasks) { this.tasks = tasks; }
}