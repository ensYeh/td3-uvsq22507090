package fr.uvsq.cprog.collex;

import java.util.List;

public class ChercherDomaine implements Commande {
    private String domaine;
    boolean trieA;

    public ChercherDomaine(String domaine , boolean trieA) {
        this.domaine = domaine;
        this.trieA = trieA;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        List<DnsItem> res =dns.getItems(domaine);
        if (res.isEmpty()) {
            tui.affiche("Aucun élément trouvé pour le domaine: " + domaine);
            return;
        }
        if (trieA) {
            res.sort((a, b) -> a.getIp().compareTo(b.getIp()));
        } else {
            res.sort((a, b) -> a.getNom().compareTo(b.getNom()));
        }
        for (DnsItem item : res) {
            tui.affiche(item.toString());
        }
    }
}
