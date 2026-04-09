package com.sms;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Read form data sent from add-student.html
        String name   = request.getParameter("name");
        String email  = request.getParameter("email");
        String phone  = request.getParameter("phone");
        String course = request.getParameter("course");
        double marks  = Double.parseDouble(request.getParameter("marks"));

        // Step 2: Build a Student object
        Student s = new Student();
        s.setName(name);
        s.setEmail(email);
        s.setPhone(phone);
        s.setCourse(course);
        s.setMarks(marks);

        // Step 3: Save to database using DAO
        StudentDAO dao     = new StudentDAO();
        boolean    success = dao.addStudent(s);

        // Step 4: Redirect back to student list
        if (success) {
            // ✅ Success — go to main page
            response.sendRedirect("ListStudentServlet");
        } else {
            // ❌ Failed — go back to form
            response.sendRedirect("add-student.html?error=true");
        }
    }
}
