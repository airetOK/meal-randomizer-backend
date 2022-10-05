package com.randomizer.meal.resource;

import com.randomizer.meal.client.RestClient;
import io.helidon.microprofile.cdi.Main;
import io.helidon.webserver.ServerRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/meal/v1")
public class MealResource
{

    private final RestClient client;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Inject
    public MealResource(RestClient client) {
        this.client = client;
    }

    @Path("/random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomMeal(@Context ServerRequest req) {
        LOGGER.info(String.format("IP remote address: %s:%d", req.remoteAddress(), req.remotePort()));
        return Response.ok(client.getRandomMeal()).build();
    }

}
