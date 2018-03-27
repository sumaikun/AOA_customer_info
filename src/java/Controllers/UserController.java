/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Core.SQLMANAGER;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.*;
import Model.cliente;
import java.lang.reflect.InvocationTargetException;


/**
 *
 * @author JVega
 */
public class UserController {
    
    private final SQLMANAGER manager;
    
    public UserController()
    {
       this.manager = new SQLMANAGER();
    }
    
    public void Userinfo(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException, ServletException, SQLException 
    {
        if(session.getAttribute("Siniestro")==null)
        {
            response.sendRedirect("index.jsp");
        }
        //System.out.println("got to user info");
        String query = "select distinct departamento from ciudad order by departamento";
        List<Map<String, String>>  departamentos = this.manager.ExecuteSql(query).fetch_query(null).get_rows();
        request.setAttribute("departamentos", departamentos);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("User_info.jsp");
        requestDispatcher.forward(request, response);
    }
    
    public void get_user_info(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException, ServletException, SQLException 
    {
        String documento = request.getParameter("documento");
        String query = "Select * from cliente where identificacion = "+documento;      
        Map<String, String> Usuario = this.manager.ExecuteSql(query).fetch_query(null).first_row();
        if(Usuario != null)
        {
            session.setAttribute("Usuario", Usuario);
            JSONObject jsonObject;
            jsonObject = new JSONObject(Usuario);        
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.flush();
        }
                
    }
    
    public void user_data(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws SQLException, ServletException, IOException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        if(session.getAttribute("Siniestro")==null)
        {
          response.sendRedirect("index.jsp");
        }
        String identificacion = request.getParameter("identificacion");        
	String lugar_expedicion = request.getParameter("lugar_expedicion");
	String nombres = request.getParameter("nombres");
	String apellidos = request.getParameter("apellidos");
	String tipo_identificacion = request.getParameter("tipo_identificacion");	
	String ciudad = request.getParameter("codigo_ciudad");
	String barrio = request.getParameter("barrio");
	String dir_domicilio = request.getParameter("dir_domicilio");
	String tel_oficina = request.getParameter("tel_oficina");
	String tel_vivienda = request.getParameter("tel_vivienda");
	String celular = request.getParameter("celular");
	String correo = request.getParameter("correo");
	String sexo = request.getParameter("sexo");
        
        String query = "Select * from cliente where identificacion = "+identificacion;
        
        Map<String, String> Usuario = this.manager.ExecuteSql(query).fetch_query(null).first_row();
        
        cliente cliente = new cliente();
        
        cliente.nombre = nombres.toUpperCase();
        cliente.apellido = apellidos.toUpperCase();
        cliente.tipo_id = tipo_identificacion;
        cliente.lugar_expdoc = lugar_expedicion.toUpperCase();
        cliente.pais = "CO";
        cliente.ciudad = ciudad;
        cliente.direccion = dir_domicilio.toUpperCase();
        cliente.barrio = barrio.toUpperCase();
        cliente.telefono_oficina = tel_oficina;
        cliente.telefono_casa = tel_vivienda;
        cliente.celular = celular;
        cliente.email_e = correo;
        cliente.sexo = sexo;
        cliente.identificacion = identificacion;
        
        if(Usuario != null)
        {  
            cliente.update("identificacion");          
        }
        else
        {
            cliente.save();
            query = "Select * from cliente where identificacion = "+identificacion;        
            Usuario = this.manager.ExecuteSql(query).fetch_query(null).first_row();
        }    
        session.setAttribute("Usuario", Usuario);
        
        response.sendRedirect("Warranty_info");
        
    }    
    
}
