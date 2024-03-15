package Q1;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/carnet")
public class Annuaire {
    private static Carnet carnet = new Carnet();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCarnet() {
        if (carnet.isEmpty()) {
            return "Liste vide";
        } else {
            return carnet.toString();
        }
    }
}
