package com.randomizer.meal;

import io.helidon.common.reactive.Single;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

@Path("/ft")
public class FtResource {

    private static int retry;

    @Fallback(fallbackMethod = "fallbackMethod")
    @Path("/fallback/{success}")
    @GET
    public Single<String> fallbackHandler(@PathParam("success") String success) {
        if (!Boolean.parseBoolean(success)) {
            deadEnd();
        }
        return reactiveData();
    }

    @Retry(maxRetries = 2)
    @Path("/retry")
    @GET
    public Single<String> retryHandler() {
        if (++retry < 2)  {
            deadEnd();
        }
        String response = String.format("failures: %s", retry);
        retry = 0;
        return Single.just(response);
    }

    private void deadEnd() {
        throw new RuntimeException("failure");
    }

    private Single<String> fallbackMethod(String success) {
        return Single.just("Fallback endpoint reached");
    }

    private Single<String> reactiveData() {
        return Single.create(this::blockingData);
    }

    private String blockingData() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        return "blocked for 100 millis";
    }

}
