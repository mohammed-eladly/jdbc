package edu.demo.jdbc;

import java.sql.*;

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
public class PreparedStatementTest {

    public static void main(String[] args) {
        try {
            // Class.forName("com.mysql.jdbc.Driver");==> com.mysql.cj.jdbc.Driver
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM customers WHERE customerName=?");
            preparedStatement.setString(1, "Atelier graphique");

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Number\tName");

            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
            }

            ///================================================
            preparedStatement = con.prepareStatement(
                    "insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1,\n"
                            + "                       addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit)\n"
                            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, 555212);
            preparedStatement.setString(2, "java team");
            preparedStatement.setString(3, "adly");
            preparedStatement.setString(4, "mohammed");
            preparedStatement.setString(5, "1234567890");
            preparedStatement.setString(6, "add1");
            preparedStatement.setString(7, "add2");
            preparedStatement.setString(8, "city");
            preparedStatement.setString(9, "state");
            preparedStatement.setString(10, "postalCode");
            preparedStatement.setString(11, "country");
            preparedStatement.setInt(12, 1370);
            preparedStatement.setDouble(13, 10000);

            int i = preparedStatement.executeUpdate();

            System.out.println("New Record Added: [" + (i == 1) + "]");

            resultSet.close();
            preparedStatement.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

