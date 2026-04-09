package com.sms; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // ── Oracle Connection Settings ────────────
    private static final String URL      = "jdbc:oracle:thin:@localhost:1521:ORCLDB";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

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
