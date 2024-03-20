package Q7;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML)
    public String createContact(Contact contact) {
        String doigts = "nom : " + contact.getNom() + " - numero : " + contact.getNumero();
        if (contact.getNom() == null || contact.getNumero() == null) {
            return "<p>Erreur : nom ou numero manquant </p>" + doigts;
        } else if (carnet.contains(contact.getNom())) {
            return "<p>Erreur : contact déjà existant</p>" + doigts;
        } else {
            carnet.addContact(contact.getNom(), contact.getNumero());
            return "<a href=\"" + contact.getNom() + "\">Fiche contact</a>" + doigts;
        }
    }

    @PUT
    @Path("updateContact/{nom}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML)
    public Response updateContact(@PathParam("nom") String nom, Contact updatedContact) {
        System.out.println("nom : " + nom + " - numero : " + updatedContact.getNumero());
        if (carnet.isEmpty() || !carnet.contains(nom)) {
            System.out.println("Contact introuvable");
            return Response.noContent().build(); // 204
        } else {
            System.out.println("Contact trouvé");
            carnet.updateContact(nom, updatedContact.getNumero());
            return Response.ok().build(); // 200
        }
    }
}
