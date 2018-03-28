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
import static java.lang.Integer.parseInt;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

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
        
        Map<String, String> Siniestro  = (Map<String, String>) session.getAttribute("Siniestro");
        query = "Select * from sin_autor where siniestro = '"+Siniestro.get("id")+"'  and estado != 'R'";
        Map<String, String>  sin_auto = this.manager.ExecuteSql(query).fetch_query(null).first_row();
        String options = "";
        if(sin_auto == null)
        {
             options = "open"; 
        }
        else
        {
             options = "close";
        }
        
        
        franqs = franqs.substring(0, franqs.length()-1);
        
        query = "Select * from franquisia_tarjeta where id in ("+franqs+")";
        List<Map<String, String>>  franquicias = this.manager.ExecuteSql(query).fetch_query(null).get_rows();
        request.setAttribute("franquicias", franquicias);
        request.setAttribute("options", options);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Warranty_info.jsp");
        requestDispatcher.forward(request, response);
    }
    
    public void credit_warranty(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException, SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
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
            Map<String, String> Aseguradora  = (Map<String, String>) session.getAttribute("Aseguradora");
            //System.out.println(Usuario);
            String query = "Select * from sin_autor where siniestro = '"+Siniestro.get("id")+"'  and estado != 'R'";
            Map<String, String>  sin_auto = this.manager.ExecuteSql(query).fetch_query(null).first_row();
            System.out.println(fieldsJson);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());	
            String now = currentTime.toString();	
            if(sin_auto == null)
            {
                autorizacion.siniestro = Siniestro.get("id");
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
                autorizacion.email = Usuario.get("email_e");
                autorizacion.devol_tipo_cuenta = fieldsJson.getString("devol_tipo_cuenta");
                autorizacion.devol_cuenta_banco = fieldsJson.getString("devol_cuenta_bancaria");
                autorizacion.devol_banco = fieldsJson.getString("devol_banco");
                autorizacion.devol_ncuenta = fieldsJson.getString("devol_nombre_titular");
                autorizacion.identificacion_devol = fieldsJson.getString("devol_iden_titular");
                autorizacion.formulario_web = "2"; 
                autorizacion.valor = Aseguradora.get("garantia");               
                autorizacion.save();
               
            }
            else
            {
                autorizacion.map_to_object(sin_auto);
                autorizacion.setId(parseInt(sin_auto.get("id")));
                autorizacion.numero = fieldsJson.getString("numero_tarjeta");
                autorizacion.franquicia = fieldsJson.getString("franquicia");
                autorizacion.banco = fieldsJson.getString("banco");
                autorizacion.vencimiento_mes =  fieldsJson.getString("month_expi");
                autorizacion.vencimiento_ano = fieldsJson.getString("year_expi");
                autorizacion.codigo_seguridad = fieldsJson.getString("cvv");
                autorizacion.devol_tipo_cuenta = fieldsJson.getString("devol_tipo_cuenta");
                autorizacion.devol_cuenta_banco = fieldsJson.getString("devol_cuenta_bancaria");
                autorizacion.devol_banco = fieldsJson.getString("devol_banco");
                autorizacion.devol_ncuenta = fieldsJson.getString("devol_nombre_titular");
                autorizacion.identificacion_devol = fieldsJson.getString("devol_iden_titular");
                autorizacion.update();
                //System.out.println("actualizar sin auto "+autorizacion.siniestro);
                
            }
            //System.out.println(sin_auto);
        } catch (JSONException ex) {
            Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void consignment_warranty(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException, SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
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
            Map<String, String> Aseguradora  = (Map<String, String>) session.getAttribute("Aseguradora");
            //System.out.println(Usuario);
            String query = "Select * from sin_autor where siniestro = '"+Siniestro.get("id")+"'  and estado != 'R'";
            Map<String, String>  sin_auto = this.manager.ExecuteSql(query).fetch_query(null).first_row();
            System.out.println(fieldsJson);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());	
            String now = currentTime.toString();
            if(sin_auto == null)
            {               
                autorizacion.siniestro = Siniestro.get("id");
                autorizacion.nombre = Usuario.get("nombre");
                autorizacion.identificacion = Usuario.get("identificacion");
                
                
                
                autorizacion.fecha_consignacion = fieldsJson.getString("fecha_consignacion").split("T")[0];
                autorizacion.numero_consignacion = fieldsJson.getString("comprobante_consignacion");
                autorizacion.franquicia = "6";
                autorizacion.fecha_solicitud = now;                
                
                
                autorizacion.solicitado_por = "Pagina web version 2";
                autorizacion.estado = "E";
                autorizacion.email = Usuario.get("email_e");
                autorizacion.devol_tipo_cuenta = fieldsJson.getString("devol_tipo_cuenta");
                autorizacion.devol_cuenta_banco = fieldsJson.getString("devol_cuenta_bancaria");
                autorizacion.devol_banco = fieldsJson.getString("devol_banco");
                autorizacion.devol_ncuenta = fieldsJson.getString("devol_nombre_titular");
                autorizacion.identificacion_devol = fieldsJson.getString("devol_iden_titular");
                autorizacion.formulario_web = "2"; 
                autorizacion.valor = Aseguradora.get("garantia_consignada");               
                autorizacion.save();
               
            }
            else
            {
                autorizacion.map_to_object(sin_auto);
                autorizacion.setId(parseInt(sin_auto.get("id")));
                autorizacion.fecha_solicitud = now; 
                autorizacion.fecha_consignacion = fieldsJson.getString("fecha_consignacion").split("T")[0];
                autorizacion.numero_consignacion = fieldsJson.getString("comprobante_consignacion");
                autorizacion.franquicia = "6";
                autorizacion.devol_tipo_cuenta = fieldsJson.getString("devol_tipo_cuenta");
                autorizacion.devol_cuenta_banco = fieldsJson.getString("devol_cuenta_bancaria");
                autorizacion.devol_banco = fieldsJson.getString("devol_banco");
                autorizacion.devol_ncuenta = fieldsJson.getString("devol_nombre_titular");
                autorizacion.identificacion_devol = fieldsJson.getString("devol_iden_titular");
                autorizacion.valor = Aseguradora.get("garantia_consignada");
                autorizacion.update();
                //System.out.println("actualizar sin auto "+autorizacion.siniestro);
                
            }
            //System.out.println(sin_auto);
        } catch (JSONException ex) {
            Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void risk_warranty(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException, IOException, SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
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
            Map<String, String> Aseguradora  = (Map<String, String>) session.getAttribute("Aseguradora");
            //System.out.println(Usuario);
            String query = "Select * from sin_autor where siniestro = '"+Siniestro.get("id")+"'  and estado != 'R'";
            Map<String, String>  sin_auto = this.manager.ExecuteSql(query).fetch_query(null).first_row();
            System.out.println(fieldsJson);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());	
            String now = currentTime.toString();
            if(sin_auto == null)
            {               
                autorizacion.siniestro = Siniestro.get("id");
                autorizacion.nombre = Usuario.get("nombre");
                autorizacion.identificacion = Usuario.get("identificacion");               
                autorizacion.fecha_consignacion = fieldsJson.getString("riesgo_fecha").split("T")[0];
                autorizacion.numero_consignacion = fieldsJson.getString("riesgo_consignacion");
                autorizacion.franquicia = "6";
                autorizacion.fecha_solicitud = now;               
                
                autorizacion.solicitado_por = "Pagina web version 2";
                autorizacion.estado = "E";
                autorizacion.email = Usuario.get("email_e");
                autorizacion.devol_tipo_cuenta = "";
                autorizacion.devol_cuenta_banco ="";
                autorizacion.devol_banco = "";
                autorizacion.devol_ncuenta = "";
                autorizacion.identificacion_devol = "0";
                autorizacion.formulario_web = "2"; 
                autorizacion.valor = Aseguradora.get("valor_no_reembols");               
                autorizacion.save();
               
            }
            else
            {
                autorizacion.map_to_object(sin_auto);
                autorizacion.setId(parseInt(sin_auto.get("id")));
                autorizacion.fecha_solicitud = now; 
                
                autorizacion.fecha_consignacion = fieldsJson.getString("riesgo_fecha").split("T")[0];
                autorizacion.numero_consignacion = fieldsJson.getString("riesgo_consignacion");
                
                autorizacion.franquicia = "6";
                autorizacion.devol_tipo_cuenta = "";
                autorizacion.devol_cuenta_banco ="";
                autorizacion.devol_banco = "";
                autorizacion.devol_ncuenta = "";
                autorizacion.identificacion_devol = "0";
                autorizacion.valor = Aseguradora.get("garantia_consignada");
                autorizacion.update();
                //System.out.println("actualizar sin auto "+autorizacion.siniestro);
                
            }
            //System.out.println(sin_auto);
        } catch (JSONException ex) {
            Logger.getLogger(ServletDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
