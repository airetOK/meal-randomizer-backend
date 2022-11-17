package com.randomizer.meal.client;

import com.randomizer.meal.entity.MealJsonResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/***
 * RestClient class is used for sending requests on URLs.
 * URLs should be read from application.yaml
 */
@ApplicationScoped
public class RestClient
{

    private final String RANDOM_MEAL;
    private final String FIRST_LETTER;

    @Inject
    public RestClient(@ConfigProperty(name = "url.meal.random") String url,
                      @ConfigProperty(name = "url.meal.fl") String fl) {
        RANDOM_MEAL = url;
        FIRST_LETTER = fl;
    }

    private final Client client = ClientBuilder.newClient();

    public MealJsonResponse getRandomMeal() {
        return get(RANDOM_MEAL);
    }

    public MealJsonResponse getMealsByFirstLetter(String firstLetter) {
        return get(FIRST_LETTER, "f", firstLetter);
    }

    private MealJsonResponse get(String url, String...params) {
        String queryParam = "";
        String value = "";
        if (params.length != 0) {
            queryParam = params[0];
            value = params[1];
        }
        return client
                .target(url)
                .queryParam(queryParam, value)
                .request(MediaType.APPLICATION_JSON)
                .get(MealJsonResponse.class);
    }

}
