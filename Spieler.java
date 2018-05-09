import java.util.*;
/**
 * Beschreiben Sie hier die Klasse Spieler.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Spieler
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String name;
    private Inventar inventar;
    
    
    /**
     * Konstruktor für Objekte der Klasse Spieler
     */
    public Spieler(String name, Inventar neuesInventar)
    {
        this.name = name;
        inventar = neuesInventar;
    }

    public void setzeInventar(String bezeichnung) 
    {
       inventar.befülleInventar(bezeichnung);
    }
    
    public String gibNamen()
    {
        return name;
    }
    
    public void gibInventarInhalt()
    {
      inventar.inhaltAusgeben();
    }
    
    public void nehmen(Gegenstand bezeichnung)
    {
        
    }
   
    public void abgeben(Gegenstand bezeichnung)
    {
        
    }
    
    public Inventar getInventar(Inventar inventar)
    {
       return inventar; 
    }
    
    
}
