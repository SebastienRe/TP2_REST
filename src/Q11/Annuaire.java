package Q11;

import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
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
    public String getNumero(@PathParam("nom") String nom) throws ContactNotFoundPlainException {
        System.out.println("nom : " + nom);
        String numero = carnet.getNumeroWithNom(nom);
        if (numero == null)
            throw new ContactNotFoundException();
        return numero;
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

    @DELETE
    @Path("/deleteContact/{nom}")
    @Produces(MediaType.TEXT_HTML)
    public Response deleteContact(@PathParam("nom") String nom) {
        if (carnet.isEmpty() || !carnet.contains(nom)) {
            System.out.println("Contact introuvable");
            return Response.noContent().build(); // 204
        } else {
            System.out.println("Contact supprimé");
            carnet.deleteContact(nom);
            return Response.ok().build(); // 200
        }
    }

    @GET
    @Path("/supp/{nom}")
    @Produces(MediaType.TEXT_HTML)
    public Response deleteContactWithCookie(@PathParam("nom") String nom) {
        if (carnet.isEmpty() || !carnet.contains(nom)) {
            System.out.println("Contact introuvable");
            return Response.noContent().build(); // 204
        } else {
            System.out.println("Contact supprimé");
            carnet.deleteContact(nom);
            // Create a new cookie with the name of the deleted contact
            NewCookie cookie = new NewCookie("deletedContact", nom, "/", "", "", NewCookie.DEFAULT_MAX_AGE, false);
            return Response.ok().cookie(cookie).build(); // 200
        }
    }

    @GET
    @Path("/dernier")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLastDeletedContact(@Context HttpHeaders headers) {
        System.out.println("getLastDeletedContact");
        if (headers.getCookies().get("deletedContact") == null) {
            return "No deleted contact";
        }
        // Retrieve the value of the "deletedContact" cookie from the request headers
        String deletedContact = headers.getCookies().get("deletedContact").getValue();
        return "Last deleted contact: " + deletedContact;
    }

    @GET
    @Path("/createCookie/{nom}")
    @Produces(MediaType.TEXT_HTML)
    public Response createCookie(@PathParam("nom") String nom) {
        // Create a new cookie with the name of the deleted contact
        NewCookie cookie = new NewCookie("deletedContact", nom);
        return Response.ok().cookie(cookie).build(); // 200
    }

    @GET
    @Path("/listCookies")
    @Produces(MediaType.TEXT_PLAIN)
    public String listCookies(@Context HttpHeaders headers) {
        System.out.println("listCookies");
        // Retrieve all cookies from the request headers
        Map<String, Cookie> cookies = headers.getCookies();
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies.values()) {
            sb.append(cookie.getName()).append(": ").append(cookie.getValue()).append("\n");
        }
        return sb.toString();
    }
}
