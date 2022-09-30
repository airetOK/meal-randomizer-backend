package com.randomizer.meal.client;

import com.randomizer.meal.entity.Meal;
import com.randomizer.meal.entity.MealJsonResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.Map;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/***
 * RestClient class is used for sending requests on URLs.
 * URLs should be read from application.yaml
 */
@ApplicationScoped
public class RestClient
{

    private final String RANDOM_MEAL;

    @Inject
    public RestClient(@ConfigProperty(name = "url.meal.random") String url) {
        RANDOM_MEAL = url;
    }

    private final Client client = ClientBuilder.newClient();

    public MealJsonResponse getRandomMeal() {
        return get(RANDOM_MEAL);
    }

    private MealJsonResponse get(String url) {
        return client
            .target(url)
            .request(MediaType.APPLICATION_JSON)
            .get(MealJsonResponse.class);
    }
}
