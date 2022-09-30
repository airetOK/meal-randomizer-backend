package com.randomizer.meal.resource;

import static org.junit.jupiter.api.Assertions.*;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
@HelidonTest
class MealResourceTest {

    @Inject
    WebTarget webTarget;

    @Test
    void testDefaultGreeting() {
        Response response = webTarget.path("/meal/v1/random").request().get();
        assertEquals(response.getStatus(), 200);
    }
}