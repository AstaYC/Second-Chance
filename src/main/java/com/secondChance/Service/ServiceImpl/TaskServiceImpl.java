package com.secondChance.Service.ServiceImpl;

import com.secondChance.DAO.DAOImpl.TaskDAOImpl;
import com.secondChance.Models.TaskEntity;
import com.secondChance.Service.ServiceInterface.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private TaskDAOImpl taskDAO;

    // Constructor or you can use Dependency Injection (e.g., Spring)
    public TaskServiceImpl() {
        this.taskDAO = new TaskDAOImpl(); // Alternatively, inject TaskDAOImpl
    }

    @Override
    public void createTask(TaskEntity task) {
        // Business logic can go here, like validation or formatting
        taskDAO.createTask(task);
    }

    @Override
    public TaskEntity getTaskById(Long id) {
        return taskDAO.getTaskById(id);
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public void updateTask(TaskEntity task) {
        // Additional logic like checking if the task exists could be added here
        taskDAO.updateTask(task);
    }

    @Override
    public void deleteTask(Long id) {
        // Example of additional business logic:
        // Check if the task exists before deleting
        TaskEntity task = taskDAO.getTaskById(id);
        if (task != null) {
            taskDAO.deleteTask(id);
        }
    }
}
