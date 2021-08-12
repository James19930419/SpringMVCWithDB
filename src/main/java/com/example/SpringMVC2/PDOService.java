package com.example.SpringMVC2;

import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.sql.SQLException;
// connection class to db
@Service
public class PDOService {
    public java.sql.Connection connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory","root","");

        return con1;
    }
}
