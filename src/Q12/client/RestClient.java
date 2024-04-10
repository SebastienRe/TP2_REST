package Q12.client;

import Q12.Contact;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

public class RestClient {

    public static void main(String[] args) {
        try {
            Client client = ClientBuilder.newClient();

            Contact newContact = new Contact("Alice", "123456");
            System.out.println("nom : " + newContact.getNom() + " - numero : " + newContact.getNumero());

            Response response = client.target("http://localhost:8080/Q12/carnet/addContact")
                    .request()
                    .post(Entity.xml(newContact));

            String message = response.readEntity(String.class);
            System.out.println(message);

            response.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
