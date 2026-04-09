package com.sms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // ─────────────────────────────────────────
    //  1. ADD a new student
    // ─────────────────────────────────────────
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (name, email, phone, course, marks) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhone());
            ps.setString(4, s.getCourse());
            ps.setDouble(5, s.getMarks());

            int rows = ps.executeUpdate();
            return rows > 0; // true = success

        } catch (SQLException e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    //  2. VIEW all students
    // ─────────────────────────────────────────
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("course"),
                    rs.getDouble("marks")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching students: " + e.getMessage());
        }
        return list;
    }

    // ─────────────────────────────────────────
    //  3. GET one student by ID (for Edit page)
    // ─────────────────────────────────────────
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student s  = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("course"),
                    rs.getDouble("marks")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error finding student: " + e.getMessage());
        }
        return s;
    }

    // ─────────────────────────────────────────
    //  4. UPDATE an existing student
    // ─────────────────────────────────────────
    public boolean updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, email=?, phone=?, course=?, marks=? " +
                     "WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhone());
            ps.setString(4, s.getCourse());
            ps.setDouble(5, s.getMarks());
            ps.setInt(6,    s.getId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error updating student: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    //  5. DELETE a student
    // ─────────────────────────────────────────
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error deleting student: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    //  6. SEARCH students by name or course
    // ─────────────────────────────────────────
    public List<Student> searchStudents(String keyword) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR course LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("course"),
                    rs.getDouble("marks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error searching: " + e.getMessage());
        }
        return list;
    }
}
