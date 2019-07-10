package octavio;

import java.sql.*;
import javax.swing.*;
public class conectar {
Connection conect = null;
   public Connection conexion()
    {
      try {
             
           //Cargamos el Driver MySQL
          Class.forName("com.mysql.jdbc.Driver");
          Class.forName("org.gjt.mm.mysql.Driver");
//conect = DriverManager.getConnection("jdbc:mysql://192.168.1.68/octavio","alday","alday");  server91.hostinger.com
  // conect = DriverManager.getConnection("jdbj:mysql://31.220.104.1/tavo","u529891088","dentoimagen1");                
//servidor-conect = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10225315","sql10225315","xUkyHmlEbY");
conect = DriverManager.getConnection("jdbc:mysql://67.227.157.152/willdent_octavio","willdent_base","k]8BSZCE&j;#"); //Cargamos el Driver Access
           //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           //Conectar en red base 
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=//servidor/bd_cw/cw.mdb";
           //Conectar Localmente
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=D:/cwnetbeans/cw.mdb";
          //conect = DriverManager.getConnection(strConect,"",""); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conect;  
}}
