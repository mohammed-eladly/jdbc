package edu.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
public class TransactionProcessingTest {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            // Class.forName("com.mysql.jdbc.Driver");==> com.mysql.cj.jdbc.Driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");
            con.setAutoCommit(false);

            preparedStatement = con.prepareStatement(
                    "insert into offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory)\n"
                            + "values (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, 111);
            preparedStatement.setString(2, "city");
            preparedStatement.setString(3, "phone");
            preparedStatement.setString(4, "add1");
            preparedStatement.setString(5, "add2");
            preparedStatement.setString(6, "state");
            preparedStatement.setString(7, "country");
            preparedStatement.setString(8, "postal code");
            preparedStatement.setString(9, "terr");

            preparedStatement.executeUpdate();

            // throwException();

            con.commit();//to commit changes
        } catch(Exception e) {
            e.printStackTrace();
            try {
                System.err.println("rolling back.");
                con.rollback();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void throwException() throws Exception {
        throw new Exception();
    }

}

