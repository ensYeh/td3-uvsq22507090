package fr.uvsq.cprog.collex;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class DnsTest {
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
}
