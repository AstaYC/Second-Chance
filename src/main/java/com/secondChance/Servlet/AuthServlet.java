package com.secondChance.Servlet;
import com.secondChance.Models.UserEntity;
import com.secondChance.Service.ServiceInterface.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.secondChance.Util.PasswordUtil;


import com.secondChance.Service.ServiceImpl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/auth") // Single URL for the AuthServlet
public class AuthServlet extends HttpServlet {
    private UserServiceImpl userService ;

    @Override
    public void init() throws ServletException {
        // Initialize the service layer
         userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                // Show the login form
                request.getRequestDispatcher("/Auth/login.jsp").forward(request, response);
                break;

            case "register":
                // Show the registration form
                request.getRequestDispatcher("/Auth/register.jsp").forward(request, response);
                break;

            default:
                // Show a default page or an error message
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found.");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                handleLogin(request, response);
                break;

            case "register":
                handleRegistration(request, response);
                break;

            case "logout":
                handleLogout(request, response);
                break;

            default:
                // Handle invalid action
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found.");
                break;
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isValidUser = userService.validateUser(email, password);

        if (isValidUser) {
            // Set session attribute if login is successful
            request.getSession().setAttribute("useremail", email);
            UserEntity user = userService.getUserByEmail(email);
            request.getSession().setAttribute("role", user.getRole());
            response.sendRedirect("User/welcome.jsp");
        } else {
            // Redirect back to login with an error if login fails
            response.sendRedirect("login.jsp?error=invalid_credentials");
        }
    }


    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve user input from the form
        String username = request.getParameter("name");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "USER";


        // Validate input (you can add more validations as necessary)
        if (username == null || email == null || password == null || firstName == null || lastName == null) {
            // If input is invalid, redirect back to registration form with an error
            request.setAttribute("error", "Please fill in all fields.");
            request.getRequestDispatcher("auth?action=register").forward(request, response);
            return;
        }

        // Check if the user with this email already exists
        UserEntity existingUser = userService.getUserByEmail(email);
        if (existingUser != null) {
            // If user already exists, redirect back to registration with an error
            request.setAttribute("error", "Email is already registered.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Hash the password using BCrypt
        String hashedPassword = PasswordUtil.hashPassword(password);

        // Create a new UserEntity object
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setRole(role);

        try {
            userService.createUser(newUser);
            response.sendRedirect("auth?action=login");
        }catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }


    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate(); // Invalidate the session
        response.sendRedirect("login.jsp"); // Redirect to login page after logout
    }
}
