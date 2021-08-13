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
    public void add(CustomerClass custToBeAdd) throws SQLException, ClassNotFoundException {
        String queryStr = "INSERT INTO savingstable VALUES ( ?, ? ,? ,?,? )";
        PreparedStatement query = DBconnection.prepareStatement(queryStr,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, custToBeAdd.getCustno());//1,2为Data Binding的'?'顺序
        query.setString(2, custToBeAdd.getCustname());
        query.setDouble(3, custToBeAdd.getCdep());
        query.setInt(4, custToBeAdd.getNyears());
        query.setString(5, custToBeAdd.getSavtype());
        query.executeUpdate();
    }

    @Override
    public CustomerClass edit(@org.jetbrains.annotations.NotNull CustomerClass catToBeUpdated, String custno) throws SQLException, ClassNotFoundException {
        PreparedStatement query;
        query = DBconnection.prepareStatement("Update savingstable set custno=?, custname=?, cdep =?,nyears=?,savtype=? where custno = ?",ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, catToBeUpdated.getCustno());
        query.setString(2, catToBeUpdated.getCustname());
        query.setDouble(3, catToBeUpdated.getCdep());
        query.setInt(4, catToBeUpdated.getNyears());
        query.setString(5, catToBeUpdated.getSavtype());
        query.setString(6, custno);
        query.executeUpdate();
        return catToBeUpdated;
    }

    @Override
    public void delete(String custno) throws SQLException {
        String quer1 = "Delete from savingstable where custno = ?";
        PreparedStatement query = DBconnection.prepareStatement(quer1,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, custno);
        query.executeUpdate();

    }

    @Override
    public List<CustomerClass> selectAllCustomersTable() throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<CustomerClass> Catlist = new ArrayList<CustomerClass>();
        String quer1 = "Select * from savingstable";
        PreparedStatement query = DBconnection.prepareStatement(quer1);
        ResultSet resultSet = query.executeQuery();
        CustomerClass oneCategory;
        //display records if there is data;
        while (resultSet.next()) {
            oneCategory = new CustomerClass(
                    resultSet.getString("custno"),
                    resultSet.getString("custname"),
                    resultSet.getDouble("cdep"),
                    resultSet.getInt("nyears"),
                    resultSet.getString("savtype")
            );
            Catlist.add(oneCategory);
        }
        return Catlist;
    }

    public CustomerClass selectOneCategoryByKey(String custno) throws SQLException,ClassNotFoundException {
        String quer1 = "Select * from savingstable where custno = ?";
        PreparedStatement query = DBconnection.prepareStatement(quer1,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        query.setString(1, custno);
        ResultSet resultSet = query.executeQuery();
        if(!resultSet.first()){
            System.out.print("Record not existing");//this will be in url
            return null;
        }
        CustomerClass oneCategory=null;
        oneCategory = new CustomerClass(
                resultSet.getString("custno"),
                resultSet.getString("custname"),
                resultSet.getDouble("cdep"),
                resultSet.getInt("nyears"),
                resultSet.getString("savtype")
        );
        return oneCategory;
    }

//    public ArrayList<InterestClass> selectAllItemsTableUnderSameCustno(String custno) throws ClassNotFoundException, SQLException {
//        ArrayList<InterestClass> allItemsInThatcustno = new ArrayList<InterestClass>();
//
//
//        return allItemsInThatcustno;
//    }
}
