package fr.uvsq.cprog.collex;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Path;

public class Dns {
    private  Path dataBaseFile;
    private List<DnsItem> items = new ArrayList<>();

    public Dns(String propertiesFile) throws IOException {
        Properties props = new Properties();
        props.load(Files.newBufferedReader(Path.of(propertiesFile)));
        String fileName = props.getProperty("dnsfile");
        if (fileName == null) {
            throw new IllegalArgumentException("Property 'dnsfile' not found");
        }
        this.dataBaseFile = Path.of(fileName);
        if (Files.exists(dataBaseFile)) {
            List<String> lines = Files.readAllLines(dataBaseFile);
            for (String ligne : lines){
                if (ligne.isBlank() || ligne.isEmpty()) {
                    continue;
                }
                String[] parts = ligne.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid line format: " + ligne);
                }
                NomMachine nomMachine = new NomMachine(parts[0]);
                AdresseIP adresseIP = new AdresseIP(parts[1]);
                DnsItem item = new DnsItem(nomMachine, adresseIP);
                items.add(item);
            }
        }
    }
    public DnsItem getItem(NomMachine nom) {
        for (DnsItem item : items) {
            if (item.getNomMachine().getNom().equals(nom.getNom())) {
                return item;
            }
        }
        return null;
    }

    public DnsItem getItem(AdresseIP ip){
        for(DnsItem item : items){
            if (item.getAdresseIP().getIp().equals(ip.getIp()))
                return item;
        }
        return null;
    }

    public List<DnsItem> getItems(String domain) {
        List<DnsItem> result = new ArrayList<>();
        for (DnsItem item : items){
            if (item.getNomMachine().getNom().endsWith(domain)){
                result.add(item);
            }
        }
        return result;
    }
    public void addItem(NomMachine nomMachine, AdresseIP adresseIP)throws IOException {
        DnsItem newItem = new DnsItem(nomMachine, adresseIP);
        if (getItem(nomMachine) != null || getItem(adresseIP) != null) {
            throw new IllegalArgumentException("Item with same machine name or IP address already exists");
        }
        items.add(newItem);
        byte[] line = (nomMachine.getNom() + " " + adresseIP.getIp() + System.lineSeparator()).getBytes();
        Files.write(dataBaseFile, line);
    }
}
