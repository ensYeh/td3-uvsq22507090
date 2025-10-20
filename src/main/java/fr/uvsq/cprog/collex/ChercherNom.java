package fr.uvsq.cprog.collex;

public class ChercherNom implements Commande {
    private String adresseIp;

    public ChercherNom(String adreseeIP) {
        this.adresseIp = adreseeIP;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
       AdresseIP ip = new AdresseIP(adresseIp);
       DnsItem item = dns.getItem(ip);
         if (item == null) {
              tui.affiche("Machine " + ip + " inconnue");
              return;
         }
       String nom = dns.getItem(ip).getNomMachine().getNom();
       if (nom == null) {
           tui.affiche("Machine " + ip + " inconnue");
           return;
       }
       tui.affiche("Nom de Machine de l'adresse " + ip + " : " + nom);
    }

}
