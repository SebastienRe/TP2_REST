package Q13;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {
    private String nom;
    private String numero;

    public Contact() {
        // Default constructor
    }

    public Contact(String nom, String numero) {
        this.nom = nom;
        this.numero = numero;
    }

    @XmlElement
    public String getNom() {
        return nom;
    }

    @XmlElement
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}