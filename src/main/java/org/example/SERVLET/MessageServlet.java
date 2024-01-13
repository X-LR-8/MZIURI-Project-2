package org.example.SERVLET;

import com.mysql.cj.protocol.Resultset;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.AddClasses.User;
import org.example.DAO.DataConnector;
import org.example.DAO.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    private DataConnector dataConnector;
    private UserDao userDao;
    @Override
    public void init() throws ServletException {
        dataConnector=new DataConnector();
        Connection connection=dataConnector.getConnection();
        userDao=UserDao.getcon(connection);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");
        String name=req.getParameter("user");
        String message=req.getParameter("message");
        if(!message.contains("\n")){
            User user=new User(name,"",message);
            if(userDao.checkUserByUsername(user)){
                try {
                    userDao.updateInbox(name,fillInbox(name,message));
                    resp.setStatus(200);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else{
                resp.setStatus(403);
            }
        }else{
            resp.setStatus(403);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        User user=new User(name,password,"");
        if(userDao.checkUser(user)){
            try {
                resp.getWriter().write(getinbox(name));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resp.setStatus(200);
        }else{

            resp.setStatus(403);
        }
    }
    public String fillInbox(String name, String message) throws SQLException {
        String inbox="";
        ResultSet r=userDao.getUser(name);
        while(r.next()){
            inbox=r.getString("inbox");
            inbox+=message+"\n";
        }
        return inbox;
    }
    public String getinbox(String name) throws SQLException {
        String inbox="";
        ResultSet r=userDao.getUser(name);
        while (r.next()){
            inbox=r.getString("inbox");
        }
        return inbox;
    }
}
