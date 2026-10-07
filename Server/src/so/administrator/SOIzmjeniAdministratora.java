/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.administrator;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Administrator;
import java.util.ArrayList;
import java.util.regex.Pattern;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOIzmjeniAdministratora extends AbstractSO{

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
             if (!(ado instanceof Administrator)) {
            throw new Exception("Proslijedjeni objekat nije instanca klase Administrator!");
        }

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        String password=a.getPassword();
        
        if (!password.matches(".*[a-zA-Z]+.*")) {
            throw new Exception("Lozinka mora da sadrzi barem jedno slovo");
        }
        
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
           throw new Exception("Lozinka mora da sadrzi barem jedan broj");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
    
}
