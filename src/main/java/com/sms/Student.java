package com.sms;

public class Student {

    private int    id;
    private String name;
    private String email;
    private String phone;
    private String course;
    private double marks;

    // ── Constructors ──────────────────────────
    public Student() {}

    public Student(int id, String name, String email,
                   String phone, String course, double marks) {
        this.id     = id;
        this.name   = name;
        this.email  = email;
        this.phone  = phone;
        this.course = course;
        this.marks  = marks;
    }

    // ── Getters (read values) ─────────────────
    public int    getId()     { return id; }
    public String getName()   { return name; }
    public String getEmail()  { return email; }
    public String getPhone()  { return phone; }
    public String getCourse() { return course; }
    public double getMarks()  { return marks; }

    // ── Setters (set values) ──────────────────
    public void setId(int id)         { this.id = id; }
    public void setName(String name)  { this.name = name; }
    public void setEmail(String email){ this.email = email; }
    public void setPhone(String phone){ this.phone = phone; }
    public void setCourse(String c)   { this.course = c; }
    public void setMarks(double m)    { this.marks = m; }

    // ── toString (useful for debugging) ───────
    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + name +
               ", course=" + course + ", marks=" + marks + "}";
    }
}
