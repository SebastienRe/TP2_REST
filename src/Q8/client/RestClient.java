package Q8.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class RestClient {

    public static void main(String[] args) {
        try {
            Client client = ClientBuilder.newClient();

            Response response = client.target("http://localhost:8080/TP2Q8/carnet/deleteContact/Jane")
                    .request()
                    .delete();

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
