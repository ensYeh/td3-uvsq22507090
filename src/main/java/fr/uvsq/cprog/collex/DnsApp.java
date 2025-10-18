package fr.uvsq.cprog.collex;

public class DnsApp {
    Dns dns;
    DnsTUI tui;
    public DnsApp(String properties)throws Exception {
        dns = new Dns(properties);
        tui = new DnsTUI();
    }
    void run (){
        while (true) {
            System.out.println("enter command : ");
           Commande cmd = tui.nextCommande();
           if (cmd == null) {
               tui.affiche("Commande inconnue");
               continue;
           }
            cmd.execute(dns, tui);
            if (cmd instanceof ExitCmd) {
                break;
            }
        }
    }
}