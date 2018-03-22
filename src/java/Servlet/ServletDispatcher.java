/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.User;
import Service.LoginService;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Controllers.SiniestroInfoController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(urlPatterns = {"/login", "/test"})
public class ServletDispatcher extends HttpServlet
{
    private SiniestroInfoController siniestrosinfocontroller ;
    
    public ServletDispatcher()
    {
        this.siniestrosinfocontroller = new SiniestroInfoController();
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        session.setAttribute("error_message",null);
          
          
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        if (servletPath.equals("/login")) {
            this.siniestrosinfocontroller.login(request,response,session);
            //response.sendRedirect("index.jsp");
        }
                
                /*String userName=request.getParameter("userName");
                String password=request.getParameter("password");
                
                LoginService loginService=new LoginService();
                
                if(loginService.isValidUser(userName, password))
                {
                User user=loginService.getUser();
                request.setAttribute("user", user);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login_success.jsp");
                requestDispatcher.forward(request, response);
                }
                else
                {
                //response.sendRedirect("index.jsp");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login_fail.jsp");
                requestDispatcher.forward(request, response);
                }*/;
    }
}
