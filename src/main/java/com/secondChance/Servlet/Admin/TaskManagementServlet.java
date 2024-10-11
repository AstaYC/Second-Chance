package com.secondChance.Servlet.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/task") // Single URL for the AuthServlet
public class TaskManagementServlet extends HttpServlet{

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Forward to the JSP form to display the create user form
    request.getRequestDispatcher("Admin/tasks.jsp").forward(request, response);
}

}
