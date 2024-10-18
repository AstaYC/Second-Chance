package com.MBAREK0.web.Service;

import com.MBAREK0.web.entity.TaskStatus;
import com.MBAREK0.web.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import com.MBAREK0.web.entity.Task;
import com.MBAREK0.web.repository.implementation.TaskRepositoryImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private TaskRepositoryImpl taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
        taskRepository = new TaskRepositoryImpl(entityManager);
        System.out.println("Test setup complete. Mocks initialized.");
    }

    @Test
    void testCreateTask() {
        // Arrange
        User user = new User();
        user.setId(1L);
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setStatus(TaskStatus.pending);
        task.setStartDate(LocalDate.now());
        task.setEndDate(LocalDate.now().plusDays(1));
        task.setUser(user);
        task.setManager(user);

        when(entityManager.merge(any(Task.class))).thenReturn(task);
        System.out.println("Arrange phase complete. Task created: " + task);

        // Act
        System.out.println("Executing createTask method...");
        Task createdTask = taskRepository.createTask(task);
        System.out.println("createTask method executed. Result: " + createdTask);

        System.out.println("Starting assertions...");

        // Assert
        verify(transaction).begin();
        System.out.println("Verified: Transaction began");
        verify(entityManager).persist(task);
        verify(transaction).commit();
        System.out.println("Verified: Transaction committed");

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(entityManager).persist(taskCaptor.capture());
        Task capturedTask = taskCaptor.getValue();
        System.out.println("Captured task in persist call: " + capturedTask);


        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getTitle());
        assertEquals("This is a test task", createdTask.getDescription());
        assertEquals(LocalDate.now(), createdTask.getStartDate());
        assertEquals(LocalDate.now().plusDays(1), createdTask.getEndDate());
        assertEquals(user, createdTask.getUser());
        assertEquals(user, createdTask.getManager());

        System.out.println("Assertions passed. Test complete.");
    }
}
