package com.afaqy.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Name : Main
 * <p>
 * Description :
 * <p>
 * Date : 29/05/2021
 * <p>
 * Create by : Mohammed ElAdly
 * <p>
 * Mail : mohammed.eladly@afaqy.com
 */
public class Main {

    public static void main(String[] args) {
        try {
            // Class.forName("com.mysql.jdbc.Driver");==> com.mysql.cj.jdbc.Driver
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from customers");

            System.out.println("Number\tName");

            while(rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

