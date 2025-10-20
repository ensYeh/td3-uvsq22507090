package fr.uvsq.cprog.collex;

public class DnsItem {
    private NomMachine nomMachine;
    private AdresseIP adresseIP;
    public DnsItem(NomMachine nomMachine, AdresseIP adresseIP) {
        this.nomMachine = nomMachine;
        this.adresseIP = adresseIP;
    }
public AdresseIP getAdresseIP() {
    return adresseIP;
}
public NomMachine getNomMachine() {
    return nomMachine;
}
@Override
public String toString() {
    return adresseIP.toString() + " " + nomMachine.toString();
}
public String getIp() {
    return adresseIP.getIp();
}
public String getNom() {
    return nomMachine.getNom();
}
}
