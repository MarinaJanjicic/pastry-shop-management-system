/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Racun;
import domen.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOSacuvajRacun extends AbstractSO{

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
      if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }

        Racun r = (Racun) ado;

        if (r.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati bar jednu stavku!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet generatedKeys = ps.getGeneratedKeys();
        generatedKeys.next();
        Long racunID=generatedKeys.getLong(1);
        
        Racun r=(Racun) ado;
        r.setRacunID(racunID);
        
        for(StavkaRacuna stavkaRacuna:r.getStavkeRacuna()){
            stavkaRacuna.setRacun(r);
            DBBroker.getInstance().insert(stavkaRacuna);
        }
    }
    
}
