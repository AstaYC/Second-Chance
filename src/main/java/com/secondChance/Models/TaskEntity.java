package com.secondChance.Models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.secondChance.Models.TagEntity;

@Entity
@Table(name = "tasks")
public class TaskEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "createdDate", nullable = false)
    private Date createdDate;

    @Column(name = "limitedDate", nullable = false)
    private Date limitedDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    // Many-to-Many relationship with TagEntity
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "task_tag", // Name of the join table
            joinColumns = { @JoinColumn(name = "task_id") }, // Foreign key for Task
            inverseJoinColumns = { @JoinColumn(name = "tag_id") } // Foreign key for Tag
    )
    private Set<TagEntity> tags = new HashSet<>(); // Many tasks can have many tags


    // Default constructor
    public TaskEntity() {}

    // Constructor with fields
    public TaskEntity(String name, String description, UserEntity user , Date createdDate , Date limitedDate) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.createdDate = createdDate;
        this.limitedDate = limitedDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
       this.createdDate = createdDate;
    }

    public Date getLimitedDate() {
        return limitedDate;
    }

    public void setLimitedDate(Date limitedDate) {
        this.limitedDate = limitedDate;
    }
    public Set<TagEntity> getTags() { return tags; }
    public void setTags(Set<TagEntity> tags) { this.tags = tags; }
}
