package org.example.DAO;

import org.example.AddClasses.User;

import java.sql.*;

public class UserDao {

    private static UserDao instance;
    private final Connection connection;


    public UserDao(Connection connection) {
        this.connection=connection;
    }

    public static UserDao getcon(Connection connection){
            if(instance==null){
                instance=new UserDao(connection);
           }
            return instance;
    }
    public ResultSet getUser(String name){
        Statement statment= null;
        try {
            statment = connection.createStatement();
            return statment.executeQuery("select * from user where username='"+name+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addUser(User user){
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = connection.prepareStatement("insert into user(username,password,inbox) values (?,?,?)");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getInbox());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkUser(User user){
        Statement statment= null;
        try {
            statment = connection.createStatement();
            ResultSet r=statment.executeQuery("select * from user where username='"+user.getName()+"' and password='"+user.getPassword()+"'");
            if(r.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkUserByUsername(User user){
        Statement statment= null;
        try {
            statment = connection.createStatement();
            ResultSet r=statment.executeQuery("select * from user where username='"+user.getName()+"'");
            if(r.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateInbox(String name,String inbox){
        Statement statment= null;
        try {
            statment = connection.createStatement();
            statment.executeUpdate("update user set inbox='"+inbox+"'where username='"+name+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
