package com.randomizer.meal.resource;

import com.randomizer.meal.client.RestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/meal")
public class MealResource
{

    private final RestClient client;

    @Inject
    public MealResource(RestClient client) {
        this.client = client;
    }

    @Path("/random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomMeal() {
        return Response.ok(client.getRandomMeal()).build();
    }

}
