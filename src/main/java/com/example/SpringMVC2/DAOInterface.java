package com.example.SpringMVC2;

import java.sql.SQLException;
import java.util.List;

//Create a Database Interface.
public interface DAOInterface {

    public void add(CustomerClass cust)throws SQLException,ClassNotFoundException;
    public CustomerClass edit(CustomerClass cust, String custno) throws SQLException,ClassNotFoundException;
    public void delete(String custno) throws SQLException;
    public List<CustomerClass> selectAllCustomersTable()throws ClassNotFoundException, SQLException;

}
