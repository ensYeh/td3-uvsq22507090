package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {
    Scanner in = new Scanner(System.in);


    public Commande nextCommande(){
        System.out.println("> ");
        String input = in.nextLine();
        String[] parts = input.split("\\s+");
        if (parts.length == 0) {
            return null;
        }
        String cmd = parts[0];
        if (cmd.equals("ajoute")) {
            if (parts.length < 3) {
                System.out.println("Usage: ajoute <nom_machine> <adresse_ip>");
                return null;
            }
            return new AjouterCmd( parts[1], parts[2]);
        }
        else if (cmd.equals("chercheip")){
            if (parts.length < 2){
                System.out.println("Usage: chercheip <domaine>");
                return null;
            }
            return new ChercherIp(parts[1]);
        }
        else if (cmd.equals("cherchenom")){
            if (parts.length < 2){
                System.out.println("Usage: cherchenom <adresse_ip>");
                return null;
            }
            return new ChercherNom(parts[1]);
        }
        else if (cmd.equals("cherchedomaine")){
            if (parts.length < 2){
                System.out.println("Usage: cherchedomaine <domaine>");
                return null;
            }
            return new ChercherDomaine(parts[1]);
        }
        else if (cmd.equals("exit")){
            return new ExitCmd();
        }
        return null;
    }

    public void affiche (String message){
        System.out.println(message);
    }
}
