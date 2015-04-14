package net.projektfriedhof.recbay.resource;


import net.projektfriedhof.recbay.model.Recomendation;
import org.glassfish.jersey.server.JSONP;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by falko on 12.03.15.
 */
@Path("myresource")
public class Myresource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces({"application/javascript", MediaType.APPLICATION_JSON})
    public Recomendation getIt() {
        Recomendation rec = new Recomendation();
       // rec.setId(666l);
       // rec.setUri("FF");
        return rec;
    }
}
