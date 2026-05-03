package com.musti.api_gateway.route;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class InventoryServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> inventoryRoutes(){
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.GET("/rest/api/inventory/events/list"),
                        HandlerFunctions.http("http://localhost:8080/rest/api/inventory/events/list"))

                .route(RequestPredicates.path("/rest/api/inventory/venue/{venueId}"),
                        request -> forwardWithPathVariable(request, "venueId", "http://localhost:8080/rest/api/inventory/venue/"))

                .route(RequestPredicates.path("/rest/api/inventory/event/{eventId}"),
                        request -> forwardWithPathVariable(request, "eventId", "http://localhost:8080/rest/api/inventory/event/"))
                .build();
    }

    private static ServerResponse forwardWithPathVariable(ServerRequest request,
                                                          String pathVariable,
                                                          String baseUrl) throws Exception{
        String value = request.pathVariable(pathVariable);
        return HandlerFunctions.http(baseUrl + value).handle(request);
    }
}
