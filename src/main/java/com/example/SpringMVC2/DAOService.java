package com.example.SpringMVC2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOService implements DAOInterface {

    Connection DBconnection;

    public DAOService(Connection con){
        this.DBconnection = con;
    }


    @Override
    public void add(CategoryClass catToBeAdd) throws SQLException, ClassNotFoundException {
        String queryStr = "INSERT INTO category VALUES ( ?, ? )";
        PreparedStatement query = DBconnection.prepareStatement(queryStr,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, catToBeAdd.getCatcode());//1,2为Data Binding的'?'顺序
        query.setString(2, catToBeAdd.getCatdesc());
        query.executeUpdate();
    }

    @Override
    public CategoryClass edit(@org.jetbrains.annotations.NotNull CategoryClass catToBeUpdated, String catcode) throws SQLException, ClassNotFoundException {
        PreparedStatement query;
        query = DBconnection.prepareStatement("Update category set catcode=?, catdesc=? where catcode = ?",ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, catToBeUpdated.getCatcode());
        query.setString(2, catToBeUpdated.getCatdesc());
        query.setString(3, catcode);
        query.executeUpdate();
        return catToBeUpdated;
    }

    @Override
    public void delete(String catcode) throws SQLException {
        String quer1 = "Delete from Category where catcode = ?";
        PreparedStatement query = DBconnection.prepareStatement(quer1,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, catcode);
        query.executeUpdate();

    }

    @Override
    public List<CategoryClass> selectAllCategoryTable() throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<CategoryClass> Catlist = new ArrayList<CategoryClass>();
        String quer1 = "Select * from category";
        PreparedStatement query = DBconnection.prepareStatement(quer1);
        ResultSet resultSet = query.executeQuery();
        CategoryClass oneCategory;
        //display records if there is data;
        while (resultSet.next()) {
            oneCategory = new CategoryClass(resultSet.getString("catcode"), resultSet.getString("catdesc"));
            Catlist.add(oneCategory);
        }
        return Catlist;
    }

    public CategoryClass selectOneCategoryByKey(String catcode) throws SQLException,ClassNotFoundException {
        String quer1 = "Select * from category where catcode = ?";
        PreparedStatement query = DBconnection.prepareStatement(quer1,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, catcode);
        ResultSet resultSet = query.executeQuery();
        if(!resultSet.first()){
            System.out.print("Record not existing");//this will be in url
            return null;
        }
        CategoryClass oneCategory=null;
        oneCategory = new CategoryClass(resultSet.getString("catcode"), resultSet.getString("catdesc"));
        return oneCategory;
    }

    public ArrayList<ItemsClass> selectAllItemsTableUnderSameCatcode(String catcode) throws ClassNotFoundException, SQLException {
        ArrayList<ItemsClass> allItemsInThatCatcode = new ArrayList<ItemsClass>();
        String quer1 = "Select itemcode,itemdesc from items where catcode=?";
        PreparedStatement query = DBconnection.prepareStatement(quer1,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, catcode);
        ResultSet resultSet = query.executeQuery();
        while (resultSet.next()){
            ItemsClass oneItem = new ItemsClass(resultSet.getString("itemcode"), resultSet.getString("itemdesc"),catcode);
            allItemsInThatCatcode.add(oneItem);
        }
        if(allItemsInThatCatcode.size()<1){
            System.out.print("Record not existing");
            return null;
        }
        else {
            return allItemsInThatCatcode;
        }
    }
}
