package com.randomizer.meal.resource;

import com.randomizer.meal.client.RestClient;
import io.helidon.microprofile.cdi.Main;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/meal")
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
    public Response getRandomMeal(@Context UriInfo uriInfo) {
        Response res = Response.ok(client.getRandomMeal()).build();
        LOGGER.info(String.format("Status code for the endpoint %s is %s", uriInfo.getAbsolutePath(), res.getStatus()));
        return res;
    }

}
