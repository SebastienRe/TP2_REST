package Q2;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class MonClient {
    public static void main(String[] args) {
        // Créer un client JAX-RS
        Client client = ClientBuilder.newClient();

        // Appeler le service REST en utilisant l'URL du service
        Response response = client.target("http://garoh-sebastienre:8080/TP2Q2/carnet/John")
                .request(MediaType.TEXT_PLAIN)
                .get();

        // Récupérer la réponse du service
        int status = response.getStatus();
        String content = response.readEntity(String.class);

        // Afficher la réponse, son statut et le contenu
        System.out.println("Statut : " + status);
        System.out.println("Contenu : " + content);

        // Fermer le client
        client.close();
    }
}