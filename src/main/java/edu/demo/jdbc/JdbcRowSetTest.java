package edu.demo.jdbc;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Name : JdbcRowSetTest
 * <p>
 * Description :
 * <p>
 * Date : 29/05/2021
 * <p>
 * Create by : Mohammed ElAdly
 * <p>
 * Mail : mohammed.eladly@afaqy.com
 */
public class JdbcRowSetTest {

    static final String DB_URL = "jdbc:mysql://localhost:3306/classicmodels";
    static final String USER_NAME = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) {
        try(JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER_NAME);
            rowSet.setPassword(PASSWORD);
            rowSet.setCommand("SELECT * FROM customers");
            rowSet.execute();

            ResultSetMetaData metaData = rowSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println("Number of Cols: " + columnCount);

            for(int i = 1; i < columnCount; i++) {
                System.out.printf("%-8s\t", metaData.getColumnName(i));
                System.out.println();
                while(rowSet.next()) {
                    for(int j = 1; j <= columnCount; j++) {
                        System.out.printf("%-8s\t", rowSet.getObject(j));
                    }
                    System.out.println();
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}