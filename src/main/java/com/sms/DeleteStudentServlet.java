// ============================================
//  FILE: src/com/sms/DeleteStudentServlet.java
//  PURPOSE: Deletes a student from the DB
//           when Delete button is clicked
// ============================================

package com.sms;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Get student ID from URL (?id=2)
        int id = Integer.parseInt(request.getParameter("id"));

        // Step 2: Delete from database
        StudentDAO dao     = new StudentDAO();
        boolean    success = dao.deleteStudent(id);

        // Step 3: Always redirect back to list
        response.sendRedirect("ListStudentServlet");
    }
}