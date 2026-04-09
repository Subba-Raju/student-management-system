package com.sms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListStudentServlet")
public class ListStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Get all students from the database
        StudentDAO   dao      = new StudentDAO();
        List<Student> students = dao.getAllStudents();

        // Step 2: Calculate stats
        int    total   = students.size();
        long   aboveA  = students.stream()
                                 .filter(s -> s.getMarks() >= 90)
                                 .count();
        double avgMarks = students.stream()
                                  .mapToDouble(Student::getMarks)
                                  .average()
                                  .orElse(0.0);

        // Step 3: Build the HTML response
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head>");
        out.println("<meta charset='UTF-8'/>");
        out.println("<title>Student Management System</title>");
        out.println("<link rel='stylesheet' href='css/style.css'/>");
        out.println("</head><body>");

        // Navbar
        out.println("<nav class='navbar'>");
        out.println("  <div class='nav-brand'>🎓 Student Management System</div>");
        out.println("  <div class='nav-links'>");
        out.println("    <a href='ListStudentServlet' class='active'>All Students</a>");
        out.println("    <a href='add-student.html'>+ Add Student</a>");
        out.println("  </div>");
        out.println("</nav>");

        out.println("<div class='container'>");

        // Page Header + Search
        out.println("<div class='page-header'>");
        out.println("  <h2>Student Records</h2>");
        out.println("  <div class='search-box'>");
        out.println("    <input type='text' id='searchInput' " +
                    "placeholder='🔍 Search by name or course...' />");
        out.println("  </div>");
        out.println("</div>");

        // Stats Cards
        out.println("<div class='stats-row'>");
        out.println("  <div class='stat-card'>");
        out.println("    <span class='stat-number'>" + total + "</span>");
        out.println("    <span class='stat-label'>Total Students</span>");
        out.println("  </div>");
        out.println("  <div class='stat-card green'>");
        out.println("    <span class='stat-number'>" + aboveA + "</span>");
        out.println("    <span class='stat-label'>Above 90%</span>");
        out.println("  </div>");
        out.println("  <div class='stat-card orange'>");
        out.printf("    <span class='stat-number'>%.1f</span>%n", avgMarks);
        out.println("    <span class='stat-label'>Average Marks</span>");
        out.println("  </div>");
        out.println("</div>");

        // Table
        out.println("<div class='table-wrapper'>");
        out.println("<table id='studentTable'>");
        out.println("<thead><tr>");
        out.println("  <th>#</th><th>Name</th><th>Email</th>");
        out.println("  <th>Phone</th><th>Course</th><th>Marks</th>");
        out.println("  <th>Grade</th><th>Actions</th>");
        out.println("</tr></thead><tbody id='tableBody'>");

        // Loop through each student and build a table row
        for (Student s : students) {
            String grade     = getGrade(s.getMarks());
            String badgeClass = getBadgeClass(s.getMarks());

            out.println("<tr>");
            out.println("  <td>" + s.getId()     + "</td>");
            out.println("  <td>" + s.getName()   + "</td>");
            out.println("  <td>" + s.getEmail()  + "</td>");
            out.println("  <td>" + s.getPhone()  + "</td>");
            out.println("  <td>" + s.getCourse() + "</td>");
            out.printf("  <td>%.2f</td>%n", s.getMarks());
            out.println("  <td><span class='badge " + badgeClass + "'>"
                        + grade + "</span></td>");
            out.println("  <td class='actions'>");
            out.println("    <a href='EditStudentServlet?id=" + s.getId()
                        + "' class='btn-edit'>Edit</a>");
            out.println("    <a href='DeleteStudentServlet?id=" + s.getId()
                        + "' onclick=\"return confirm('Delete this student?')\" "
                        + "class='btn-delete'>Delete</a>");
            out.println("  </td>");
            out.println("</tr>");
        }

        out.println("</tbody></table></div>");
        out.println("</div>"); // /container

        // Live search script
        out.println("<script>");
        out.println("document.getElementById('searchInput')" +
                    ".addEventListener('keyup', function() {");
        out.println("  const kw   = this.value.toLowerCase();");
        out.println("  const rows = document.querySelectorAll('#tableBody tr');");
        out.println("  rows.forEach(r => {");
        out.println("    r.style.display = " +
                    "r.innerText.toLowerCase().includes(kw) ? '' : 'none';");
        out.println("  });");
        out.println("});");
        out.println("</script>");

        out.println("</body></html>");
    }

    // ── Grade helpers ───────────────────────
    private String getGrade(double marks) {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        return "F";
    }

    private String getBadgeClass(double marks) {
        if (marks >= 90) return "a";
        if (marks >= 75) return "b";
        if (marks >= 60) return "c";
        return "f";
    }
}
