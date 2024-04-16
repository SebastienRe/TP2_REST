package Q13.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import Q13.Contact;

public class RestClient {

    public static void main(String[] args) {
        try {
            Client client = ClientBuilder.newClient();
            HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("User1", "a");
            client.register(feature);

            Contact newContact = new Contact("Alice", "123456");
            System.out.println("nom : " + newContact.getNom() + " - numero : " + newContact.getNumero());

            Response response = client.target("http://localhost:8080/TP2Q13/carnet/addContact")
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
