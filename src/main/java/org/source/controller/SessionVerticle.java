package org.source.controller;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.source.entity.SessionApp;
import org.source.service.SessionService;

public class SessionVerticle extends AbstractVerticle {
    private static final Logger LOGGER=LoggerFactory.getLogger(SessionVerticle.class);
    @Override
    public void start() throws Exception{
        LOGGER.info("Ato @start izao");

        //Create router
        Router router=Router.router(vertx);

        //Assing Controller function
        router.get("/api/v1/session").handler(this::getSession);

        //Router handler
        vertx.createHttpServer().requestHandler(router).listen(8000);
    }
    @Override
    public void stop() throws Exception{
        LOGGER.info("Ato @stop izao");
    }
    private void getSession(RoutingContext routingContext){
        LOGGER.info("Check session....");
        SessionService s=new SessionService();
        final JsonObject jsonResponse=new JsonObject();
        jsonResponse.put("sessionid",s.getSessionId());
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type","application/json")
                .end(Json.encodePrettily(jsonResponse));
    }
}
