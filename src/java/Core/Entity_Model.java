/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import Core.SQLMANAGER;
import java.sql.SQLException;
import java.util.Map;


/**
 *
 * @author JVega
 */
public abstract class Entity_Model {
    private String table;
    private String id_name = "id";
    private int id;
    private final SQLMANAGER manager;
    
    public Entity_Model()
    {
        this.manager = new SQLMANAGER();
    }
    
    public void setTable(String table)
    {
        this.table = table;
    }
    
    public String getTable()
    {
        return this.table;
    }
    
    public String getIdname()
    {
        return this.id_name;
    }
    
    public void setIdname(String idname)
    {
         this.id_name = idname;
    }
    
    public void  setId(int id) {
        this.id = id;
    }

    public String  getId() {
        return Integer.toString(id);
    }
    
    public void save() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException
    {     
       Class klazz = this.getClass();
       Object object = this;
       
       Method method_table = object.getClass().getMethod("getTable");
      
       String table = (String) method_table.invoke(object, null);
       
       String sql = "Insert into "+table+"";
       sql += "(";
       Field[] fields = klazz.getDeclaredFields();       
        for (Field field : fields) {            
              sql +=  field.getName()+",";            
        }
        sql = sql.substring(0, sql.length()-1);
        sql += ") VALUES ( ";
        for (Field field : fields) {            
              String property_method =  "get"+field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
              Method method = object.getClass().getMethod(property_method,null);
              String property = (String) method.invoke(object, null);
              if(property == null)              
              {
                  sql +=  property+",";;
              }
              else
              {
                  sql +=  "'"+property+"',";
              }
                          
        }
        sql = sql.substring(0, sql.length()-1);
        sql += ")";
        
        this.manager.ExecuteSql(sql);
        //System.out.println(sql);
    }
    
    public void update() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException
    {     
       Class klazz = this.getClass();
       Object object = this;
       
       Method method_table = object.getClass().getMethod("getTable");
      
       String table = (String) method_table.invoke(object, null);
       
       String sql = "Update "+table+"";
       sql += " SET ";
       Field[] fields = klazz.getDeclaredFields();       
        for (Field field : fields) {
           
                String property_method =  "get"+field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Method method = object.getClass().getMethod(property_method,null);
                String property = (String) method.invoke(object, null);
                sql +=  field.getName()+" = '"+property+"',";  
            
        }
        sql = sql.substring(0, sql.length()-1);
        
        method_table = object.getClass().getMethod("getIdname");
      
        String id_name = (String) method_table.invoke(object, null);
        Method method = object.getClass().getMethod("getId",null);
        String property = (String) method.invoke(object, null);
        
        sql += " Where "+id_name+"  = "+property;
        this.manager.ExecuteSql(sql);
        //System.out.println(sql);
    }
    
    public void update(String update_by_param) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException
    {     
       Class klazz = this.getClass();
       Object object = this;
       
       Method method_table = object.getClass().getMethod("getTable");
      
       String table = (String) method_table.invoke(object, null);
       
       String sql = "Update "+table+"";
       sql += " SET ";
       Field[] fields = klazz.getDeclaredFields();       
        for (Field field : fields) {          
            String property_method =  "get"+field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            Method method = object.getClass().getMethod(property_method,null);
            String property = (String) method.invoke(object, null);
            sql +=  field.getName()+" = '"+property+"',";                                   
        }
        sql = sql.substring(0, sql.length()-1);
        
        method_table = object.getClass().getMethod("getIdname");
      
        String property_method =  "get"+update_by_param.substring(0, 1).toUpperCase() + update_by_param.substring(1);
        Method method = object.getClass().getMethod(property_method,null);
        String property = (String) method.invoke(object, null);
        
        sql += " Where "+update_by_param+"  = '"+property+"'";
        this.manager.ExecuteSql(sql);
        //System.out.println(sql);
    }
    
    public void map_to_object(Map<String, String> map)
    {
    
    }
    
     public static void main(String[] args) {
         
         inspect(Entity_Model.class);
    }
    public static <T> void inspect(Class<T> klazz) {
        Field[] fields = klazz.getDeclaredFields();
        System.out.printf("%d fields:%n", fields.length);
        for (Field field : fields) {
            System.out.printf("%s %s %s%n",
                Modifier.toString(field.getModifiers()),
                field.getType().getSimpleName(),
                field.getName()
            );
        }
    }
}