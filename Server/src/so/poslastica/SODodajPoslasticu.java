/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.poslastica;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Poslastica;
import domen.Sastojak;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SODodajPoslasticu extends AbstractSO{

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Poslastica)) {
            throw new Exception("Proslijedjeni objekat nije instanca klase Poslastica!");
        
        }
        
        Poslastica p = (Poslastica) ado;
        
          if (p.getCenaPoKomadu() < 100 || p.getCenaPoKomadu() > 5000) {
            throw new Exception("Cijena mora biti izmedju 100din i 5000din!");
        }

        if (p.getSastojci().size() < 3) {
            throw new Exception("Poslastica mora da ima barem tri sastojka!");
        }

        ArrayList<Poslastica> poslastice = (ArrayList<Poslastica>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Poslastica poslastica : poslastice) {
            if (poslastica.getNaziv().equals(p.getNaziv())) {
                throw new Exception("Ovaj naziv poslastice vec postoji!");
            }
        }
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet generatedKeys = ps.getGeneratedKeys();
        generatedKeys.next();
        Long poslasticaID=generatedKeys.getLong(1);
        
        Poslastica p=(Poslastica) ado;
        p.setPoslasticaID(poslasticaID);
        
        for(Sastojak sastojak:p.getSastojci()){
            sastojak.setPoslastica(p);
            DBBroker.getInstance().insert(sastojak);
        }
    }
    
}
