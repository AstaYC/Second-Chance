package com.secondChance.DAO.Interface;

import com.secondChance.Models.TaskEntity;
import java.util.List;

public interface TaskInterface {

    void createTask(TaskEntity task);

    TaskEntity getTaskById(Long id);

    List<TaskEntity> getAllTasks();

    void updateTask(TaskEntity task);

    void deleteTask(Long id);
}
