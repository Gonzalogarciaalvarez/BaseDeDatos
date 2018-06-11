/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ggarciaalvarez
 */
public class SqLite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException{
        //Cargar el sqlite-jdbc driver usando la clase de carga
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
        //Crear la conexion con la base de datos
      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
      Statement statement = connection.createStatement();

      statement.executeUpdate("drop table if exists person");
      statement.executeUpdate("create table person (id integer, name string)");
      statement.executeUpdate("insert into person values("+JOptionPane.showInputDialog(null,"Dime el id ")+", 'Gon')");
      statement.executeUpdate("insert into person values("+JOptionPane.showInputDialog(null, "Dime el id")+", 'Alvaro')");
      ResultSet rs = statement.executeQuery("select * from person");
      while(rs.next())
      {
        // Se lee el resultado de la tabla
        System.out.println("nombre = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
      }
    }
    catch(SQLException e)
    {
      // Si salta el error de "out of memory", 
      // Seguramente significa que no se encuentra la base de datos
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // Fallo de cierre de conexion.
        System.err.println(e);
      }
    }
        
        
        
    }
    
}
