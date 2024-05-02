/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginprokon.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection con;

    public static Connection getDBConn() {
        if (con != null) {
            return con;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/proyek_konsultasi";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // or handle the exception accordingly
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null; // or handle the exception accordingly
        }
    }

    public static Connection getDBStatus() {
        return con;
    }
}
