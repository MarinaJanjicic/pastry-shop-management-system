/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbb;
import domen.Administrator;
import domen.AbstractDomainObject;
import domen.BrojPoslastica;
import domen.Racun;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Korisnik
 */
public class DBBroker {
    
    private static DBBroker instance;
    private Connection connection;

    private DBBroker() {
       try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    } 
    
    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join() + " " + ado.uslovZaSelect();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.vratiListu(rs);
    }

    public PreparedStatement insert(AbstractDomainObject ado) throws SQLException {
        String upit = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(AbstractDomainObject ado) throws SQLException {
        String upit = "UPDATE " + ado.nazivTabele() + " SET "
                + ado.vrednostiZaUpdate() + " WHERE " + ado.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    public void delete(AbstractDomainObject ado) throws SQLException {
        String upit = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    

    public ArrayList<Racun> vratiRacune(Administrator a) throws SQLException {
      ArrayList<Racun> lista=new ArrayList<>();
      String upit="SELECT * FROM RACUN R WHERE ADMINISTRATORID="+a.getAdministratorID();
      
      Statement st=connection.createStatement();
      ResultSet rs=st.executeQuery(upit);
      while(rs.next()){
          Racun r=new Racun(rs.getLong(1), rs.getDate(2), rs.getDouble(3), a, null);
          lista.add(r);
      }
      return lista;
    }

    public ArrayList<BrojPoslastica> vratiBrojPoslastica() throws SQLException {
        ArrayList<BrojPoslastica> lista=new ArrayList<>();
        String upit="SELECT TP.NAZIV, COUNT(*) AS BROJ_POSLASTICA FROM TIPPOSLASTICE TP JOIN POSLASTICA P ON (TP.TIPPOSLASTICEID=P.TIPPOSLASTICEID) GROUP BY TP.NAZIV";
        
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        while(rs.next()){
            BrojPoslastica bp=new BrojPoslastica(rs.getString(1), rs.getInt(2));
            lista.add(bp);
        }
        return lista;
    }

    
    
    
}
