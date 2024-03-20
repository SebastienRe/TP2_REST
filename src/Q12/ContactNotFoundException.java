package Q12;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ContactNotFoundException extends WebApplicationException {
    public ContactNotFoundException() {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("Ce contact est inconnu")
                .build());
    }

    @Override
    public Response getResponse() {
        return super.getResponse();
    }
}