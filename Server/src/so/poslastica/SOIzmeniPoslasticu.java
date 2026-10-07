/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.poslastica;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOIzmeniPoslasticu extends AbstractSO{

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
    
    
    
    
    

}
