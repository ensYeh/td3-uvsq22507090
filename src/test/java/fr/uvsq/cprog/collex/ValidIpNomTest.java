package fr.uvsq.cprog.collex;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class ValidIpNomTest {
    @Test(expected = IllegalArgumentException.class)
    public void testValidIp() {
        AdresseIP ip = new AdresseIP("256.100.50.25");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidNom(){
        NomMachine machine = new NomMachine(null);
    }
}
