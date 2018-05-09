import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Diese Klasse modelliert Räume in der Welt von Zuul.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Räumen über Ausgänge verbunden.
 * Für jeden existierenden Ausgang hält ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (März 2003)
 */

class Raum 
{
    private String beschreibung;
    private HashMap ausgaenge;  
    private Inventar inventar;

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausgänge.
     * @param beschreibung enthält eine Beschreibung in der Form
     *        "in einer Küche" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung, Inventar rauminhalt) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap();
        inventar = rauminhalt;
    }

    /**
     * Definiere einen Ausgang für diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der über diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    public void setzeInhalt(String bezeichnung)
    {
        inventar.befülleInventar(bezeichnung);
    }
    
    public void löscheInhalt(String bezeichnung)
    {
        inventar.entferneAusInventar(bezeichnung);
    }
    
    public boolean enthältGegenstand(String bezeichnung)
    {
        return inventar.hatGegenstand(bezeichnung);
    }
    
    /**
     * Liefere die Beschreibung dieses Raums (die dem Konstruktor
     * übergeben wurde).
     */
    public String gibKurzbeschreibung()
    {
        return beschreibung;
        
    }

    /**
     * Liefere eine lange Beschreibung dieses Raums, in der Form:
     *     Sie sind in der Küche.
     *     Ausgänge: nord west
     */
    public String gibLangeBeschreibung()
    {
        return "Sie sind " + beschreibung + ".\n" + gibAusgaengeAlsString();
    }

    /**
     * Liefere eine Zeichenkette, die die Ausgänge dieses Raums
     * beschreibt, beispielsweise
     * "Ausgänge: north west".
     */
    private String gibAusgaengeAlsString()
    {
        String ergebnis = "Ausgänge:";
        Set keys = ausgaenge.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            ergebnis += " " + iter.next();
        return ergebnis;
    }

    /**
     * Liefere den Raum, den wir erreichen, wenn wir aus diesem Raum
     * in die angegebene Richtung gehen. Liefere 'null', wenn in
     * dieser Richtung kein Ausgang ist.
     * @param richtung die Richtung, in die gegangen werden soll.
     */
    public Raum gibAusgang(String richtung) 
    {
        return (Raum)ausgaenge.get(richtung);
    }
    
    public void gibInhalt()
    {
       inventar.inhaltAusgeben(); 
    }
    
    public String gibInhaltAlsString()
    {
        String ergebnis = inventar.inhaltAusgeben();
        return ergebnis;
    }
}

