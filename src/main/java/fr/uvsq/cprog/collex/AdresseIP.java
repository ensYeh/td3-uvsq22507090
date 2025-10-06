package fr.uvsq.cprog.collex;

public class AdresseIP {
    private String ip;
    public AdresseIP(String ip) {
        if (!ip.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b")) {
            throw new IllegalArgumentException("Invalid IP address");
        }
        this.ip = ip;
    }
    public String getIp() {
        return ip;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdresseIP)) return false;
        AdresseIP other = (AdresseIP) obj;
        return this.ip.equals(other.ip);
    }
    @Override
    public int hashCode() {
        return ip.hashCode();
    }
}
