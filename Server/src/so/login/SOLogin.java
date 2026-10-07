/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Administrator;
import java.sql.SQLException;
import java.util.ArrayList;
import logika.Kontroler;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOLogin extends AbstractSO{
    
 
    
    Administrator ulogovani;

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Proslijedjeni objekat nije instanca klase Administrator!");
        }
        
        Administrator a=(Administrator) ado;
        
        for (Administrator administrator : Kontroler.getInstance().getUlogovaniAdministratori()) {
            if (administrator.getUsername().equals(a.getUsername())) {
                throw new Exception("Ovaj administrator je vec ulogovan na sistem!");
            }
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
               Kontroler.getInstance().getUlogovaniAdministratori().add(administrator);
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima!"
                + "Pokusajte ponovo!");

    }

    public Administrator getUlogovani() {
        return ulogovani;
    }
    
   
    
    
}
