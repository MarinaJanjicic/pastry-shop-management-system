/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class BrojPoslastica implements Serializable{
    
    private String nazivTipa;
    private int brojPoslastica;

    public BrojPoslastica(String nazivTipa, int brojPoslastica) {
        this.nazivTipa = nazivTipa;
        this.brojPoslastica = brojPoslastica;
    }

    public BrojPoslastica() {
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    public int getBrojPoslastica() {
        return brojPoslastica;
    }

    public void setBrojPoslastica(int brojPoslastica) {
        this.brojPoslastica = brojPoslastica;
    }
    
}
