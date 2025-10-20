package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {
    Scanner in;
    public DnsTUI(){
        in = new Scanner(System.in);
    }
    public DnsTUI(Scanner in) {
        this.in = in;
    }

    public Commande nextCommande(){
        System.out.print("> ");
        String input = in.nextLine();
        String[] parts = input.split(" ");
        String cmd = parts[0];
        if (cmd.equals("add")) {
            if (parts.length < 3) {
                System.out.println("Usage: ajoute <nom_machine> <adresse_ip>");
                return null;
            }
            return new AjouterCmd( parts[1], parts[2]);
        }
        else if (cmd.equals("ls")){
            if (parts.length < 2){
                System.out.println("Usage: ls [-a] <domaine>");
                return null;
            }
            if (parts.length == 2)
                return new ChercherDomaine(parts[1], false);
            else if (parts.length ==3 && parts[1].equals("-a"))
                return new ChercherDomaine(parts[2], true);
        }
        else if (cmd.equals("exit")){
            return new ExitCmd();
        }
        else {
            if (cmd.matches("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b")){
                return new ChercherNom(cmd);
            }
            return new ChercherIp(cmd);
            }
            return null;
    }

    public void affiche (String message){
        System.out.println(message);
    }
}
