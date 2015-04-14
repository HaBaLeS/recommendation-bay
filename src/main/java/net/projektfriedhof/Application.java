package net.projektfriedhof;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by falko on 12.03.15.
 */
@ApplicationPath("resources")
public class Application extends ResourceConfig {
    public Application() {

        //https://github.com/jersey/jersey/tree/2.16/examples/json-jackson/src/main/java/org/glassfish/jersey/examples/jackson

        packages("net.projektfriedhof.recbay.resource");
        register(JacksonFeature.class);

    }


}