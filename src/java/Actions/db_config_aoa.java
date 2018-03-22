/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

/**
 *
 * @author JVega
 */
public class db_config_aoa {
    protected String description = "Parametros de configuración de base de datos AOA";
    private final String driver;
    private final String connectString="jdbc:mysql://192.192.181.251/aoacol_aoacars?zeroDateTimeBehavior=convertToNull";
    private final String user="aoacol_arturo";
    private final String password="AOA0l1lwpdaa";
    private final String test = "Select * from pais";

    public db_config_aoa() {
        this.driver = "com.mysql.jdbc.Driver";
    }
    
    public String getDriver()
    {
        return this.driver;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public String getConnectString()
    {
        return this.connectString;
    }
    
    public String getUser()
    {
        return this.user;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public String getTest()
    {
        return this.test;
    }
    
}
