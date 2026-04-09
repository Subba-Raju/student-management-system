package com.sms;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {

    // ── GET: Show pre-filled edit form ───────
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Get the student ID from the URL (?id=3)
        int id = Integer.parseInt(request.getParameter("id"));

        // Step 2: Fetch that student from DB
        StudentDAO dao = new StudentDAO();
        Student    s   = dao.getStudentById(id);

        if (s == null) {
            response.sendRedirect("ListStudentServlet");
            return;
        }

        // Step 3: Render the edit form pre-filled with student data
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String[] courses = {
            "B.Tech CSE","B.Tech ECE","B.Tech MECH",
            "B.Tech CIVIL","BCA","MCA","MBA"
        };

        out.println("<!DOCTYPE html><html lang='en'><head>");
        out.println("<meta charset='UTF-8'/>");
        out.println("<title>Edit Student – SMS</title>");
        out.println("<link rel='stylesheet' href='css/style.css'/>");
        out.println("</head><body>");

        out.println("<nav class='navbar'>");
        out.println("  <div class='nav-brand'>🎓 Student Management System</div>");
        out.println("  <div class='nav-links'>");
        out.println("    <a href='ListStudentServlet'>All Students</a>");
        out.println("    <a href='add-student.html'>+ Add Student</a>");
        out.println("  </div></nav>");

        out.println("<div class='container'><div class='form-card'>");
        out.println("<h2>✏️ Edit Student</h2>");
        out.println("<p class='form-subtitle'>Update details and click Save Changes.</p>");

        // Form posts to same servlet (POST method handles update)
        out.println("<form action='EditStudentServlet' method='POST'>");
        out.println("  <input type='hidden' name='id' value='" + s.getId() + "'/>");

        out.println("  <div class='form-group'>");
        out.println("    <label>Full Name</label>");
        out.println("    <input type='text' name='name' value='"
                    + s.getName() + "' required/>");
        out.println("  </div>");

        out.println("  <div class='form-row'>");
        out.println("    <div class='form-group'>");
        out.println("      <label>Email Address</label>");
        out.println("      <input type='email' name='email' value='"
                    + s.getEmail() + "' required/>");
        out.println("    </div>");
        out.println("    <div class='form-group'>");
        out.println("      <label>Phone Number</label>");
        out.println("      <input type='tel' name='phone' value='"
                    + s.getPhone() + "' maxlength='10' required/>");
        out.println("    </div>");
        out.println("  </div>");

        out.println("  <div class='form-row'>");
        out.println("    <div class='form-group'>");
        out.println("      <label>Course</label>");
        out.println("      <select name='course' required>");
        for (String c : courses) {
            String selected = c.equals(s.getCourse()) ? " selected" : "";
            out.println("        <option value='" + c + "'" + selected
                        + ">" + c + "</option>");
        }
        out.println("      </select>");
        out.println("    </div>");
        out.println("    <div class='form-group'>");
        out.println("      <label>Marks (%)</label>");
        out.printf("      <input type='number' name='marks' value='%.2f' "
                    + "min='0' max='100' step='0.01' required/>%n",
                    s.getMarks());
        out.println("    </div>");
        out.println("  </div>");

        out.println("  <div class='form-actions'>");
        out.println("    <button type='submit' class='btn-primary'>💾 Save Changes</button>");
        out.println("    <a href='ListStudentServlet' class='btn-secondary'>✖ Cancel</a>");
        out.println("  </div>");
        out.println("</form></div></div></body></html>");
    }

    // ── POST: Save updated data to DB ────────
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Read updated form values
        int    id     = Integer.parseInt(request.getParameter("id"));
        String name   = request.getParameter("name");
        String email  = request.getParameter("email");
        String phone  = request.getParameter("phone");
        String course = request.getParameter("course");
        double marks  = Double.parseDouble(request.getParameter("marks"));

        // Step 2: Build updated Student object
        Student s = new Student(id, name, email, phone, course, marks);

        // Step 3: Update in database
        StudentDAO dao     = new StudentDAO();
        boolean    success = dao.updateStudent(s);

        // Step 4: Redirect to list
        response.sendRedirect("ListStudentServlet");
    }
}
