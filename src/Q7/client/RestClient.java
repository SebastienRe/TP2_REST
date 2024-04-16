package Q7.client;

import Q7.Contact;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

public class RestClient {

    public static void main(String[] args) {
        try {
            Client client = ClientBuilder.newClient();

            Contact newContact = new Contact("Jane", "4");
            System.out.println("nom : " + newContact.getNom() + " - numero : " + newContact.getNumero());

            Response response = client.target("http://localhost:8080/TP2Q7/carnet/updateContact/" + newContact.getNom())
                    .request()
                    .put(Entity.xml(newContact));

            String message = response.readEntity(String.class);
            // display response status
            System.out.println(response.getStatus());
            System.out.println(message);

            response.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
