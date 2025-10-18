package fr.uvsq.cprog.collex;

import java.util.List;

public class ChercherDomaine implements Commande {
    private String domaine;

    public ChercherDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        List<DnsItem> res =dns.getItems(domaine);
        if (res.isEmpty()) {
            tui.affiche("Aucun élément trouvé pour le domaine: " + domaine);
        } else {
            for (DnsItem item : res) {
                tui.affiche(item.toString());
            }
        }
    }
}
