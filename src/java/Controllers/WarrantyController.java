/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Core.SQLMANAGER;
import Servlet.ServletDispatcher;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import Model.autorizacion;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author JVega
 */
public class WarrantyController {
    private final SQLMANAGER manager;
    
    public WarrantyController()
    {
        this.manager = new SQLMANAGER();
    }
    
    public void warranty_info(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException, SQLException
    {
        String oficina;
        Map<String, String> Cita  = (Map<String, String>) session.getAttribute("Cita");
        String query = "select * from ciudad_franq where oficina = "+Cita.get("oficina");
        List<Map<String, String>>  ciu_franquicias = this.manager.ExecuteSql(query).fetch_query(null).get_rows();
        System.out.println(ciu_franquicias.toString());
        String franqs = "";
        
        for (Map<String, String> item : ciu_franquicias) {
            if(item.get("franquicia") != null)
            {
                franqs += item.get("franquicia")+",";
            }            
        }
        
        franqs = franqs.substring(0, franqs.length()-1);
        
        query = "Select * from franquisia_tarjeta where id in ("+franqs+")";
        List<Map<String, String>>  franquicias = this.manager.ExecuteSql(query).fetch_query(null).get_rows();
        request.setAttribute("franquicias", franquicias);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Warranty_info.jsp");
        requestDispatcher.forward(request, response);
    }
    
    public void credit_warranty(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException, SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        //Toca con un stringbuilder cuando el request viene de angular
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }   
        JSONObject fieldsJson;
        try {
            fieldsJson = new JSONObject(buffer.toString());
            autorizacion autorizacion = new autorizacion();
            Map<String, String> Siniestro  = (Map<String, String>) session.getAttribute("Siniestro");
            Map<String, String> Usuario  = (Map<String, String>) session.getAttribute("Usuario");
            //System.out.println(Usuario);
            String query = "Select * from sin_autor where siniestro = '"+Siniestro.get("id")+"'  and estado != 'R'";
            Map<String, String>  sin_auto = this.manager.ExecuteSql(query).fetch_query(null).first_row();
            System.out.println(fieldsJson);
            if(sin_auto == null)
            {
                autorizacion.Siniestro = Siniestro.get("id");
                autorizacion.nombre = Usuario.get("nombre");
                autorizacion.identificacion = Usuario.get("identificacion");
                autorizacion.numero = fieldsJson.getString("numero_tarjeta");
                autorizacion.franquicia = fieldsJson.getString("franquicia");
                autorizacion.banco = fieldsJson.getString("banco");
                autorizacion.vencimiento_mes =  fieldsJson.getString("month_expi");
                autorizacion.vencimiento_ano = fieldsJson.getString("year_expi");
                autorizacion.codigo_seguridad = fieldsJson.getString("cvv");
                autorizacion.solicitado_por = "Pagina web version 2";
                autorizacion.estado = "E";
                autorizacion.save();
               
            }
            else
            {
            
            }
            //System.out.println(sin_auto);
        } catch (JSONException ex) {
            Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
