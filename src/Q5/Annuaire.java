package Q5;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
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

    @POST
    @Path("/addContact")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String createContact(@FormParam("nom") String nom, @FormParam("numero") String numero) {
        System.out.println("nom : " + nom + " numero : " + numero);
        if (nom == null || numero == null) {
            return "<p>Erreur : nom ou numero manquant</p>";
        } else if (carnet.contains(nom)) {
            return "<p>Erreur : contact déjà existant</p>";
        } else {
            carnet.addContact(nom, numero);
            return "<a href=\"" + nom + "\">Fiche contact</a>";
        }
    }
}
