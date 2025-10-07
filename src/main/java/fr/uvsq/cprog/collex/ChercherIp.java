package fr.uvsq.cprog.collex;

public class ChercherIp implements Commande {
    private String nomM;

    public ChercherIp(String nom) {
        this.nomM = nom;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        NomMachine nomMachine = new NomMachine(nomM);
        String ip = dns.getItem(nomMachine).getAdresseIP().getIp();
        if (ip == null) {
            tui.affiche("Machine " + nomM + " inconnue");
            return;
        }
        tui.affiche("Adresse IP de " + nomM + " : " + ip);
    }

}
