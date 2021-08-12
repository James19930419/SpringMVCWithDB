package com.example.SpringMVC2;

import java.sql.SQLException;
import java.util.List;

//Create a Database Interface.
public interface DAOInterface {

    public void add(CategoryClass cat)throws SQLException,ClassNotFoundException;
    public CategoryClass edit(CategoryClass cat, String catcode) throws SQLException,ClassNotFoundException;
    public void delete(String catcode) throws SQLException;
    public List<CategoryClass> selectAllCategoryTable()throws ClassNotFoundException, SQLException;

}
