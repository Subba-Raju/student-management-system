// ============================================
//  DBConnection.java — ORACLE DATABASE
//  Use this in ALL your projects:
//  Project 1, 6, 7A, 7B, 7C
//
//  Just change the schema/table names
//  per project — connection stays the same
// ============================================

package com.sms; // ← Change package per project:
                 //   com.sms   → Project 1
                 //   com.auth  → Project 6
                 //   com.todo  → Project 7A
                 //   com.cricket → Project 7B
                 //   com.quiz  → Project 7C

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // ── Oracle Connection Settings ────────────
    private static final String URL      = "jdbc:oracle:thin:@localhost:1521:ORCLDB";
    private static final String USERNAME = "advjava";
    private static final String PASSWORD = "subba";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Step 1: Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Step 2: Connect to Oracle DB
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("✅ Oracle DB connected successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Oracle Driver not found!");
            System.out.println("   → Make sure ojdbc11.jar is in WEB-INF/lib/");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Oracle Connection failed!");
            System.out.println("   → Check URL, username, password");
            e.printStackTrace();
        }
        return conn;
    }
}