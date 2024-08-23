package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface MySQLDBConnection {

    default Connection getConnection(){
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/gymsystem";

        Connection connection = null;

        try{

            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, "root", "");

        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return connection;
    }

}
