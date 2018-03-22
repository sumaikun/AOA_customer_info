/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Core.SQLMANAGER;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
/**
 *
 * @author JVega
 */
public class SiniestroInfoController {
    private final SQLMANAGER manager;
    
    public SiniestroInfoController()
    {
       this.manager = new SQLMANAGER();
    }
    
    public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException, ServletException 
    {
        
        String placa = request.getParameter("placa");
        String celular = request.getParameter("celphone");
        String query = "Select * from siniestro where placa like '"+placa+"' and declarante_celular = "+celular;
        //System.out.println(query);
        try {
            ResultSet result = this.manager.ExecuteSql(query);
            //this.manager.print_resultdata(result);
            List<Map<String, String>> Siniestros = this.manager.fetch_query(result);
          
            if(Siniestros != null)
            {
                Map<String, String> Siniestro = Siniestros.get(0);             
                
                int estado = Integer.parseInt(Siniestro.get("estado"));
                switch(estado){
                    case 1:
                        session.setAttribute("error_message","La placa "+placa+" se encuentra en estado NO ADJUDICADO");
                        response.sendRedirect("index.jsp");
                        break;
                    case 5:
                        session.setAttribute("error_message","La placa "+placa+" se encuentra en estado PENDIENTE, no le ha sido adjudicado un servicio");
                        response.sendRedirect("index.jsp");
                        break;
                    case 7:
                        session.setAttribute("error_message","La placa "+placa+" se encuentra en servicio no requiere garantia");
                        response.sendRedirect("index.jsp");
                        break;
                    case 8:
                        session.setAttribute("error_message","La placa "+placa+" se encuentra en servicio concluido no requiere datos de garantia");
                        response.sendRedirect("index.jsp");
                        break;
                    case 3:
                        System.out.println("Empieza el servicio");                      
                        this.generate_customer_info_interface(request, response, session, Siniestro);
                        break;    
                    default:
                        session.setAttribute("error_message","La placa "+placa+" se encuentra en servicio concluido no requiere datos de garantia");
                        response.sendRedirect("index.jsp");
                        break;                
                }               
                
            }
            else
            {
                System.out.println("nulo");
                session.setAttribute("error_message","No encontramos una garantia relacionada a los datos entregados");
                response.sendRedirect("index.jsp");
            }
            // System.out.println(Arrays.toString(objects.get(0)));
        } catch (SQLException ex) {
            Logger.getLogger(SiniestroInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
   

    }
    
    public void generate_customer_info_interface(HttpServletRequest request,HttpServletResponse response,HttpSession session,Map<String, String> Siniestro) throws SQLException, ServletException, IOException
    {
        ResultSet result;
        String query = "Select * from aseguradora where id = "+Siniestro.get("aseguradora");
        result = this.manager.ExecuteSql(query);
        List<Map<String, String>> Aseguradoras = this.manager.fetch_query(result);
        Map<String, String> Aseguradora = Aseguradoras.get(0);
        query = "select concat(departamento,' ',nombre) as nciu from ciudad where codigo='"+Siniestro.get("ciudad")+"'";
        //System.out.println(query);
        result = this.manager.ExecuteSql(query);
        List<Map<String, String>> Ciudades = this.manager.fetch_query(result);
        Map<String, String> Ciudad = Ciudades.get(0);
        session.setAttribute("idsiniestro",Siniestro.get("id"));
        request.setAttribute("Siniestro", Siniestro);
        request.setAttribute("Aseguradora", Aseguradora);
        request.setAttribute("fec_siniestro", Siniestro.get("fec_siniestro"));
        request.setAttribute("Ciudad", Ciudad);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("InformacionSiniestro.jsp");
        requestDispatcher.forward(request, response);
    }
    
}
