/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;


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
import Controllers.UserController;
import Controllers.WarrantyController;
import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Actions.Helpers.SimpleJSON;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;


@WebServlet(urlPatterns = {"/login", "/test","/User","/get_user_info","/get_by_code","/get_ciudades","/user_data","/Warranty_info","/credit_warranty","/devol_data","/consignment_warranty","/risk_warranty","/logout"})
public class ServletDispatcher extends HttpServlet
{
    private SiniestroInfoController siniestrosinfocontroller ;
    private UserController usercontroller;
    private WarrantyController warrantycontroller;
    
    public ServletDispatcher()
    {
        this.siniestrosinfocontroller = new SiniestroInfoController();
        this.usercontroller = new UserController();
        this.warrantycontroller = new WarrantyController();
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
        if (servletPath.equals("/get_user_info")) {
            try {
                this.usercontroller.get_user_info(request,response,session);
                //response.sendRedirect("index.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (servletPath.equals("/get_by_code")) {
            try {
                this.siniestrosinfocontroller.get_by_code(request,response,session);
                //response.sendRedirect("index.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (servletPath.equals("/get_ciudades")) {
            try {
                this.siniestrosinfocontroller.get_ciudades(request,response,session);
                //response.sendRedirect("index.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (servletPath.equals("/user_data")) {
            try {
                this.usercontroller.user_data(request,response,session);
                //response.sendRedirect("index.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (servletPath.equals("/credit_warranty")) {           
            try {
                try {
                    try {
                        this.warrantycontroller.credit_warranty(request,response,session);
                    } catch (NoSuchFieldException ex) {
                        Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (servletPath.equals("/consignment_warranty")) { 
            try {
                this.warrantycontroller.consignment_warranty(request,response,session);
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (servletPath.equals("/risk_warranty")) { 
            try {
                this.warrantycontroller.risk_warranty(request,response,session);
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
                
              
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession();        
        session.setAttribute("error_message",null);         
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        if (servletPath.equals("/User")) {
            if(null == session.getAttribute("Siniestro")){                
                System.out.println("session  nula");
                response.sendRedirect("index.jsp");
                return;
            }
            try {
                //System.out.println("Servlet de usuario");
                this.usercontroller.Userinfo(request, response, session);
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (servletPath.equals("/Warranty_info")) {
            if(null == session.getAttribute("Siniestro")){                
                System.out.println("session  nula");
                response.sendRedirect("index.jsp");
                return;
            }
           
            try {
                this.warrantycontroller.warranty_info(request, response, session);
            } catch (SQLException ex) {
                Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (servletPath.equals("/logout")) {
           session.invalidate();
           response.sendRedirect("index.jsp");
        }
     
        
        
    }
}
