/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Actions.db_config_aoa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JVega
 */
public class SQLMANAGER {
    private Statement stmt;
    private db_config_aoa db_config = new db_config_aoa();
    private Connection con;
    
    public SQLMANAGER()
    {
        Dbconfig db = new Dbconfig(this.db_config.getDriver(),this.db_config.getConnectString(),this.db_config.getUser(),this.db_config.getPassword());
        this.con = db.connect();
    }
    
    public ResultSet ExecuteSql(String query) throws SQLException
    {
        System.out.println(query);
        ResultSet result;
        this.stmt = this.con.createStatement();
        result = this.stmt.executeQuery(query);
        //this.con.close();
        return result;
    }
    
    public void print_resultdata(ResultSet result)
    {
        try {
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = result.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            System.out.println("error en print_resultdata");
            Logger.getLogger(SQLMANAGER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Map<String, String>> fetch_query(ResultSet result)
    {
        try {
            ResultSetMetaData rsmd = result.getMetaData();
            List<Map<String, String>> records;            
            records = new ArrayList<>();
            int cols = result.getMetaData().getColumnCount();
            while (result.next()) {
                 Map<String, String> map = new HashMap<>();
                   for(int i=1; i<=cols; i++){
                    System.out.println("Columna "+rsmd.getColumnName(i)+" valor "+result.getString(i));
                    map.put(rsmd.getColumnName(i), result.getString(i));
                  }
                records.add(map);              
            }            
            if(records.size()>0)
            {
                //System.out.println("id registro "+records.get(0).get("id"));
                return records;
            }         
            else
            {
                return null;
            }            
        } catch (SQLException ex) {
            Logger.getLogger(SQLMANAGER.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
