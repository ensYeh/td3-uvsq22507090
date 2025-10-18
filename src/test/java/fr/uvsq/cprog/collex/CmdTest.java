package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;

public class CmdTest {
   
    @Test(expected = Exception.class)
    public void testAjoutMachine() throws Exception {
        // Arrange
        Dns dns = new Dns("config.properties");
        DnsTUI tui = new DnsTUI();
        AjouterCmd cmd = new AjouterCmd("20.30.40.50", "machine.fr");

        // Act
        cmd.execute(dns, tui);

        // Assert
        assertNotNull(dns.getItem(new NomMachine("machine.fr")));
        assertEquals("20.30.40.50", dns.getItem(new NomMachine("machine.fr")).getAdresseIP().getIp());
        AjouterCmd cmdInvalid = new AjouterCmd("999.30.40.50", "machine.fr");
        cmdInvalid.execute(dns, tui);
    }

    private static final String FILE_PATH = "dnsfile.txt"; // ou config.properties

    @After
    public void cleanUp() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        List<String> updated = new ArrayList<>();

        for (String line : lines) {
            // on garde toutes les lignes sauf celle qu'on veut supprimer
            if (!line.contains("machine.fr")) {
                updated.add(line);
            }
        }

        Files.write(Paths.get(FILE_PATH), updated, StandardOpenOption.TRUNCATE_EXISTING);
    }
     @Test(expected = IllegalArgumentException.class)
    public void testValidIp() {
        AdresseIP ip = new AdresseIP("256.100.50.25");
        AdresseIP ip1 = new AdresseIP("25a.100.50.25");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidNom(){
        NomMachine nom1 = new NomMachine("test.fr");
        NomMachine nom2 = new NomMachine("test.fr");
        AdresseIP ip1 = new AdresseIP("192.65.70.30");
        int hash1, hash2;
        hash1 = nom1.hashCode();
        hash2 = nom2.hashCode();
        assertEquals(hash1, hash2);
        assertEquals(nom1, nom2);
        assertEquals(nom1, nom1);
        assertFalse(nom1.equals(ip1));
        assertEquals("test.fr", nom1.toString());
        assertEquals("test.fr", nom2.getNom());
        NomMachine machine = new NomMachine(null);
        NomMachine machine1 = new NomMachine(" ");
    }
    @Test
    public void testIpEqual() throws Exception {
    AdresseIP ip1 = new AdresseIP("192.65.70.30");
    AdresseIP ip2 = new AdresseIP("192.65.70.30");
    NomMachine nom1 = new NomMachine("machineA");
    int hash1, hash2;
    hash1 = ip1.hashCode();
    hash2 = ip2.hashCode();
    assertEquals(hash1, hash2);
    assertEquals(ip1, ip2);
    assertEquals(ip1, ip1);
    assertFalse(ip1.equals(nom1));
    assertEquals("192.65.70.30", ip1.toString());
    }

    @Test
    public void testChercherDomaineTrouve() {
        // Mock des dépendances
    Dns dns = mock(Dns.class);
    DnsTUI tui = mock(DnsTUI.class);

        // Simulation du comportement de dns
    when(dns.getItems("fr")).thenReturn(Arrays.asList(new DnsItem(new NomMachine("machine.fr"), new AdresseIP("20.30.40.50"))));

        // Exécution de la commande
    Commande cmd = new ChercherDomaine("fr");
    cmd.execute(dns, tui);

        // Vérification : DnsTUI doit afficher le bon texte
    verify(tui).affiche("20.30.40.50 machine.fr");
    }
 @Test
    public void testChercherIpTrouvee() {
        Dns dns = mock(Dns.class);
        DnsTUI tui = mock(DnsTUI.class);

        when(dns.getItem(new NomMachine("machine.fr")))
            .thenReturn(new DnsItem(new NomMachine("machine.fr"), new AdresseIP("20.30.40.50")));

        Commande cmd = new ChercherIp("machine.fr");
        cmd.execute(dns, tui);

        verify(tui).affiche("Adresse IP de machine.fr : 20.30.40.50");
    }
    private Dns dns;
    @Test
    public void testGetItemByName() throws IOException {
        dns = new Dns("config.properties");
        DnsItem item = dns.getItem(new NomMachine("ecampus.uvsq.fr"));
        assertNotNull(item);
        assertEquals("193.51.25.12", item.getAdresseIP().getIp());
    }

    @Test
    public void testGetItemByIP() throws IOException {
        dns = new Dns("config.properties");
        DnsItem item = dns.getItem(new AdresseIP("193.51.31.154"));
        assertNotNull(item);
        assertEquals("poste.uvsq.fr", item.getNomMachine().getNom());
    }

    @Test
    public void testGetItemsByDomain() throws IOException {
        dns = new Dns("config.properties");
        List<DnsItem> items = dns.getItems("uvsq.fr");
        assertEquals(3, items.size());
        assertEquals("www.uvsq.fr", items.get(2).getNomMachine().getNom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemDuplicate() throws IOException {
        dns = new Dns("config.properties");
        dns.addItem(new NomMachine("machine1.local"), new AdresseIP("192.168.1.1"));
    }

    @Test
    public void testAddItemNew() throws IOException {
        dns = new Dns("config.properties");
        NomMachine newName = new NomMachine("new.local");
        AdresseIP newIp = new AdresseIP("8.8.8.8");
        dns.addItem(newName, newIp);
        assertNotNull(dns.getItem(newName));
    }
    @After
    public void clean() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        List<String> updated = new ArrayList<>();

        for (String line : lines) {
            // on garde toutes les lignes sauf celle qu'on veut supprimer
            if (!line.contains("new.local")) {
                updated.add(line);
            }
        }

        Files.write(Paths.get(FILE_PATH), updated, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
