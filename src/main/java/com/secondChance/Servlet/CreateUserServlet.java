package com.secondChance.Servlet;

import com.secondChance.Models.UserEntity;
import com.secondChance.Service.ServiceInterface.UserService;
import com.secondChance.Service.ServiceImpl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
    private UserServiceImpl userService;

    @Override
    public void init() throws ServletException {
        // Initialize the service layer
        userService = new UserServiceImpl();
    }

    // This method handles GET requests and forwards the user to the JSP form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the JSP form to display the create user form
        request.getRequestDispatcher("createUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create UserEntity object and set values
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(password);

        // Use the service to save the user
        userService.createUser(user);

        // Redirect or send a success message
        response.sendRedirect("success.jsp"); // You can create a success page or redirect to another JSP
    }
}
