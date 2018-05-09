import java.util.*;
public class Inventar
{
   //private int anzahlGegenst�nde;
   ArrayList<String> inventarListe = new ArrayList<String>();
   
   public void bef�lleInventar(String bezeichnung)
   {
     inventarListe.add(bezeichnung);  
   }

   public void entferneAusInventar(String bezeichnung)
   {
    inventarListe.remove(bezeichnung);
   }
   
   public boolean hatGegenstand(String bezeichnung)
   {
     if (inventarListe.contains(bezeichnung))
     return true;
     else 
     return false;
   }
   
   public void inhaltAusgebenAlsString()
   {
     String ergebnis = "Inhalt: ";
        for (int i = 0;i<inventarListe.size();i++)
       {
        ergebnis += " " + inventarListe.get(i);
        System.out.println(ergebnis);
       }
   }
   
   public String inhaltAusgeben()
   {
       String ergebnis = "";
        for (int i = 0;i<inventarListe.size();i++)
       {
        ergebnis += " " + inventarListe.get(i);
       }
       return ergebnis;
   }
}