class Spiel 
{
    private Parser parser;
    private Raum aktuellerRaum;
    private Spieler aktuellerSpieler;
        
    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        raeumeAnlegen();
        parser = new Parser();
    }

    /**
     * Erzeuge alle Räume und verbinde ihre Ausgänge miteinander.
     */
    private void raeumeAnlegen()
    {
        Raum r1, r2, r3, r4, r5 ,r6, r7, r8;
        Inventar i1, i2, i3, i4, i5, i6 ,i7, i8;
        
        i1 = new Inventar();
        i2 = new Inventar();
        i3 = new Inventar();
        i4 = new Inventar();
        i5 = new Inventar();
        i6 = new Inventar();
        i7 = new Inventar();
        i8 = new Inventar();
        
        r1 = new Raum("am Ausgang", i1);
        r2 = new Raum("in einem Kik", i2);
        r3 = new Raum("in einem REWE" ,i3);
        r4 = new Raum("bei den Toiletten",i4);
        r5 = new Raum("in einem McDonalds",i5);
        r6 = new Raum("in einer Abstellkammer",i6);
        r7 = new Raum("in einem K&K",i7 );
        r8 = new Raum("in einem Lagerraum",i8 );
        
        
        i2.befülleInventar("Tshirt");
        
        i3.befülleInventar("Apfel");
        
        i8.befülleInventar("Bier");
        
        r1.setzeAusgang("south", r3);

        r2.setzeAusgang("south", r4);

        r3.setzeAusgang("north", r1);
        r3.setzeAusgang("up" , r7);

        r4.setzeAusgang("north", r2);
        r4.setzeAusgang("up", r8);
        
        r5.setzeAusgang("south", r7);
        r5.setzeAusgang("east", r6);
        
        r6.setzeAusgang("west" , r5);
        r6.setzeAusgang("south", r8);

        r7.setzeAusgang("down", r3);
        r7.setzeAusgang("east", r8);
        r7.setzeAusgang("north", r5);
        
        r8.setzeAusgang("down", r4);
        r8.setzeAusgang("west", r7);
        r8.setzeAusgang("north", r6);

        aktuellerRaum = r8;  
    }

    
    private void spielerAnlegen()
    {
       Spieler s1;
       Inventar iSpieler;
       
       iSpieler = new Inventar();
       s1 = new Spieler("Spieler", iSpieler );
       
       aktuellerSpieler = s1;
    }
    
    /**
     * Die Hauptmethode zum Spielen. Läuft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und führen sie aus, bis das Spiel beendet wird.
                
        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Auf Wiedersehen!");
    }

    /**
     * Einen Begrüßungstext für den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Sie haben sich in einem Einkaufszentrum verlaufen. ");
        System.out.println("Finden sie einen Weg hinaus. Viel Glück!");
        System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
        System.out.println();
        System.out.println(aktuellerRaum.gibLangeBeschreibung());
    }

    /**
     * Verarbeite einen gegebenen Befehl (führe ihn aus).
     * Wenn der Befehl das Spiel beendet, wird 'true' zurückgeliefert,
     * andernfalls 'false'.
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        if(befehl.istUnbekannt()) {
            System.out.println("Ich weiß nicht, was Sie meinen...");
            return false;
        }

        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help"))
            hilfstextAusgeben();
        else if (befehlswort.equals("go"))
            wechsleRaum(befehl);
        else if (befehlswort.equals("inspect"))
            inspiziereRaum(befehl);
        else if (befehlswort.equals("take"))
            nehmeGegenstand(befehl);
        else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        return moechteBeenden;
    }

    // Implementierung der Benutzerbefehle:

    private void hilfstextAusgeben() 
    {
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
        parser.zeigeBefehle();
    }

    /**
     * Versuche, den Raum zu wechseln. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    private void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin möchten Sie gehen?");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);

        if (naechsterRaum == null)
            System.out.println("Dort ist keine Tür!");
        else {
            aktuellerRaum = naechsterRaum;
            System.out.println(aktuellerRaum.gibLangeBeschreibung());
        }
    }
    
    private void nehmeGegenstand(Befehl befehl)
    {
       if(!befehl.hatZweitesWort()) {
            System.out.println("Was möchten Sie nehmen?");
            return;
        } 
        
       String gegenstand = befehl.gibZweitesWort();
       
       if (aktuellerRaum.enthältGegenstand(gegenstand))
       {
           aktuellerSpieler.setzeInventar(gegenstand);
           aktuellerRaum.löscheInhalt(gegenstand);
        }
        
       else {
           System.out.println("Dieser Gegenstand existiert nicht");
        }
    }
    
    private void inspiziereRaum(Befehl befehl)
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Ich verstehe Sie nicht.");
            return;
        }
        
        System.out.println("Sie sehen folgende Gegenstände: "+ aktuellerRaum.gibInhaltAlsString());
    }
    

    /**
     * "quit" wurde eingegeben. Überprüfe den Rest des Befehls,
     * ob das Spiel wirklich beendet werden soll. Liefere 'true',
     * wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        else
            return true;  // Das Spiel soll beendet werden.
    }
}
