package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleHelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("text/plain");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print("Hello form Simple Hello Servlet");
        out.flush();
    }
}