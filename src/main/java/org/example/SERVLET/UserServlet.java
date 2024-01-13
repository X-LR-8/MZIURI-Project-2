package org.example.SERVLET;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.AddClasses.User;
import org.example.DAO.DataConnector;
import org.example.DAO.UserDao;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/register")
public class UserServlet extends HttpServlet {
    private DataConnector dataConnector;
    private UserDao userDao;
    @Override
    public void init() throws ServletException {
        dataConnector=new DataConnector();
        Connection connection=dataConnector.getConnection();
        userDao=UserDao.getcon(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        if(name.equals("") || password.equals("")){
            resp.setStatus(403);
        }else{
            User user=new User(name,password,"");
            if(userDao.checkUserByUsername(user)){
                resp.setStatus(403);
            }else{
                userDao.addUser(user);
                resp.setStatus(200);
            }
        }
    }
}
