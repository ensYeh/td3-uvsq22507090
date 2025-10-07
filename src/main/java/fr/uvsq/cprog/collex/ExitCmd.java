package fr.uvsq.cprog.collex;

public class ExitCmd implements Commande {
    @Override
    public void execute(Dns dns, DnsTUI tui) {
        System.out.println("Au revoir!");
    }

}
