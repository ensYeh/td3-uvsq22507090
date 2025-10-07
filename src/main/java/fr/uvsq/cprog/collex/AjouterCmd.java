package fr.uvsq.cprog.collex;

import java.io.IOException;

public class AjouterCmd implements Commande {
   String nomMachine;
    String adresseIP;
    public AjouterCmd(String nomMachine, String adresseIP) {
       this.nomMachine = nomMachine;
        this.adresseIP = adresseIP;
    }
    @Override
    public void execute(Dns dns, DnsTUI tui) {
        AdresseIP ip = new AdresseIP(adresseIP);
        NomMachine nom = new NomMachine(nomMachine);
        try {
            dns.addItem(nom, ip);
        } catch (IOException e) {
           tui.affiche("un error en ajoute " + e.getMessage()); ;
        }
        tui.affiche("Ajout de " + nomMachine + " avec l'adresse IP " + adresseIP);
    }

}
