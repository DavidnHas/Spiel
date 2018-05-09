import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Diese Klasse modelliert R�ume in der Welt von Zuul.
 * 
 * Ein "Raum" repr�sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen R�umen �ber Ausg�nge verbunden.
 * F�r jeden existierenden Ausgang h�lt ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (M�rz 2003)
 */

class Raum 
{
    private String beschreibung;
    private HashMap ausgaenge;  
    private Inventar inventar;

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausg�nge.
     * @param beschreibung enth�lt eine Beschreibung in der Form
     *        "in einer K�che" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung, Inventar rauminhalt) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap();
        inventar = rauminhalt;
    }

    /**
     * Definiere einen Ausgang f�r diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der �ber diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    public void setzeInhalt(String bezeichnung)
    {
        inventar.bef�lleInventar(bezeichnung);
    }
    
    public void l�scheInhalt(String bezeichnung)
    {
        inventar.entferneAusInventar(bezeichnung);
    }
    
    public boolean enth�ltGegenstand(String bezeichnung)
    {
        return inventar.hatGegenstand(bezeichnung);
    }
    
    /**
     * Liefere die Beschreibung dieses Raums (die dem Konstruktor
     * �bergeben wurde).
     */
    public String gibKurzbeschreibung()
    {
        return beschreibung;
        
    }

    /**
     * Liefere eine lange Beschreibung dieses Raums, in der Form:
     *     Sie sind in der K�che.
     *     Ausg�nge: nord west
     */
    public String gibLangeBeschreibung()
    {
        return "Sie sind " + beschreibung + ".\n" + gibAusgaengeAlsString();
    }

    /**
     * Liefere eine Zeichenkette, die die Ausg�nge dieses Raums
     * beschreibt, beispielsweise
     * "Ausg�nge: north west".
     */
    private String gibAusgaengeAlsString()
    {
        String ergebnis = "Ausg�nge:";
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

