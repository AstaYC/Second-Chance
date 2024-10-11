package com.secondChance.Servlet.Admin;

import com.secondChance.Models.TaskEntity;
import com.secondChance.Models.UserEntity;
import com.secondChance.Service.ServiceImpl.TaskServiceImpl;
import com.secondChance.Service.ServiceImpl.UserServiceImpl;
import com.secondChance.Service.ServiceInterface.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Name;
import java.io.IOException;
import java.util.List;

@WebServlet("/task") // The URL for Task operations
public class TaskServlet extends HttpServlet {
    private TaskServiceImpl taskService;
    private UserServiceImpl userService;

    @Override
    public void init() throws ServletException {
        // Initialize the TaskService
        taskService = new TaskServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "list":
                // List all tasks
                listTasks(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found.");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                // Handle task creation
                createTask(request, response);
                break;

            case "update":
                // Handle task update
                updateTask(request, response);
                break;

            case "delete":
                deleteTask(request , response);

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found.");
                break;
        }
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract task data from the form
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        // Validate input
        if (name == null || description == null || name.trim().isEmpty() || description.trim().isEmpty()) {
            request.setAttribute("error", "Title and Description cannot be empty.");
            request.getRequestDispatcher("/task?action=list").forward(request, response);
            return;
        }

        // Create a new TaskEntity object
        TaskEntity task = new TaskEntity();
        task.setName(name);
        task.setDescription(description);

        // Save the task using TaskService
        taskService.createTask(task);
        response.sendRedirect("task?action=list"); // Redirect to task list
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all tasks from the TaskService
        List<TaskEntity> taskList = taskService.getAllTasks();

        // Set the task list as an attribute to the request
        request.setAttribute("taskList", taskList);

        // Forward the request to the JSP page for rendering
        request.getRequestDispatcher("/Admin/tasks.jsp").forward(request, response);
    }


    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long taskId = (long) Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int UserId = Integer.parseInt(request.getParameter("userId"));

        // Validate input
        if (name == null || description == null || name.trim().isEmpty() || description.trim().isEmpty()) {
            request.setAttribute("error", "Title and Description cannot be empty.");
            request.getRequestDispatcher("/task?action=list").forward(request, response);
            return;
        }

        // Fetch the existing task from the database
        TaskEntity task = taskService.getTaskById(taskId);
        UserEntity user = userService.getUserById(UserId);
        if (task != null) {
            task.setName(name);
            task.setDescription(description);
            task.setUser(user);
            taskService.updateTask(task);
            response.sendRedirect("task?action=list"); // Redirect to task list
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
        }
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long taskId = (long) Integer.parseInt(request.getParameter("id"));

        // Fetch the task by its ID and delete it
        TaskEntity task = taskService.getTaskById(taskId);
        if (task != null) {
            taskService.deleteTask(taskId);
            response.sendRedirect("task?action=list"); // Redirect to task list after deletion
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
        }
    }
}
