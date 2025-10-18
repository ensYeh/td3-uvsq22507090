package fr.uvsq.cprog.collex;

public class NomMachine {
    private String nom;
    public NomMachine(String nom) {
       if (nom == null || nom.isBlank()) {
           throw new IllegalArgumentException("Invalid machine name");
       }
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof NomMachine)) return false;
        NomMachine other = (NomMachine) obj;
        return this.nom.equals(other.nom);
    }
    @Override
    public int hashCode() {
        return nom.hashCode();
    }
    @Override
    public String toString() {
        return nom;
    }
}
