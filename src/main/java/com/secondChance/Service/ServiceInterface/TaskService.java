package com.secondChance.Service.ServiceInterface;

import com.secondChance.Models.TaskEntity;

import java.util.List;

public interface TaskService {
    void createTask(TaskEntity task);

    TaskEntity getTaskById(Long id);

    List<TaskEntity> getAllTasks();

    void updateTask(TaskEntity task);

    void deleteTask(Long id);
}
