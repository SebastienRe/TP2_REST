package Q2;

import java.util.ArrayList;
import java.util.List;

public class Carnet {
    private List<Contact> contacts;

    public Carnet() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("John", "2"));
        contacts.add(new Contact("Jane", "3"));
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact c : contacts) {
            sb.append(c.getNom()).append(" : ").append(c.getNumero()).append("\n");
        }
        return sb.toString();
    }

    public String getNumeroWithNom(String nom) {
        for (Contact c : contacts) {
            if (c.getNom().equals(nom)) {
                return c.getNumero();
            }
        }
        return null;
    }
}
