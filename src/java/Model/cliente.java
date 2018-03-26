/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Core.Entity_Model;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *0
 * @author JVega
 */
public class cliente extends Entity_Model{
    
    public cliente()
    {
        setTable("cliente");
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public String getLugar_expdoc() {
        return lugar_expdoc;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getTelefono_oficina() {
        return telefono_oficina;
    }

    public String getTelefono_casa() {
        return telefono_casa;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail_e() {
        return email_e;
    }

    public String getSexo() {
        return sexo;
    }
    
  
    
    public String  getIdentificacion() {
        return identificacion;
    }
    
    
    public String nombre;
    public String apellido;
    public String tipo_id;
    public String lugar_expdoc;
    public String pais;
    public String ciudad;
    public String direccion;
    public String barrio;
    public String telefono_oficina;
    public String telefono_casa;
    public String celular;
    public String email_e;
    public String sexo;
    public String identificacion;
  
    public static void main(String[] args) {
        cliente cliente = new cliente();
        try {
            cliente.inspect(cliente.getClass());
            //inspect(cliente.class);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
