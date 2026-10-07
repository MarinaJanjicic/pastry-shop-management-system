/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.administrator;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Administrator;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOObrisiAdministratora extends AbstractSO{

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
