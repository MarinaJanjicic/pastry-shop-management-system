/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transfer.util;

/**
 *
 * @author Korisnik
 */
public interface Operacije {
    
    public static final int LOGIN = 0;
    
    public static final int DODAJ_ADMINISTRATORA=1;
    public static final int VRATI_ADMINISTRATORE=2;
    public static final int OBRISI_ADMINISTRATORA=3;
    public static final int IZMJENI_ADMINISTRATORA=4;
    
    public static final int DODAJ_TIP_POSLASTICE=5;
    public static final int VRATI_TIPOVE_POSLASTICA=6;
    public static final int OBRISI_TIP_POSLASTICE=7;
    public static final int IZMJENI_TIP_POSLASTICE=8;
    
    public static final int DODAJ_POSLASTICU=9;
    public static final int VRATI_POSLASTICE=10;
    public static final int IZMENI_POSLASTICU=11;
    
    public static final int VRATI_SASTOJKE=12;
    
    public static final int SACUVAJ_RACUN=13;
    
    public static final int ODJAVA=14;
}
