package Q2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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

    @GET
    @Path("/{nom}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getNumero(@PathParam("nom") String nom) {
        System.out.println("nom : " + nom);
        String numero = carnet.getNumeroWithNom(nom);
        if (numero == null) {
            return "Inconnu";
        } else {
            return numero;
        }
    }
}
