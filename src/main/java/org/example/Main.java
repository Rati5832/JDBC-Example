package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionUrl = "jdbc:mysql://127.0.0.1:3306/" + "user?" + "user=test&" + "password=test";

            connection = DriverManager.getConnection(connectionUrl);

            if (connection != null) {

                ResultSet resultSet = null;
                Statement statement = connection.createStatement();


                String selectSql = "SELECT * from user";
                resultSet = statement.executeQuery(selectSql);


                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " "
                            +resultSet.getString(3) + " " + resultSet.getString(4));
                    }

                resultSet.close();

                String insertSql = "INSERT INTO USER(firstname,lastname,age) VALUES('John','Test','25')";
                int rowsInserted = statement.executeUpdate(insertSql);

                String updateSql = "UPDATE USER SET lastname = 'Cage' WHERE age = 25";
                int rowsUpdated = statement.executeUpdate(updateSql);

                if(rowsUpdated > 0){

                    System.out.println("Sucessfully Updated!");

                }

                String deleteSql = "DELETE FROM USER WHERE id IN (22, 26)";
                int rowsDeleted = statement.executeUpdate(deleteSql);
                if(rowsDeleted > 0){

                    System.out.println("Sucessfully Deleted!");

                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        finally {
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
}
