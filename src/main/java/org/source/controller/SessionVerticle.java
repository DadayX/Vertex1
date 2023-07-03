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
    private SessionService s;
    private String sessionID;
    @Override
    public void start() throws Exception{
        this.s=new SessionService();
        LOGGER.info("Ato @start izao");

        //Create router
        Router router=Router.router(vertx);

        //Assing Controller function
        router.post("/api/v1/injection").handler(this::runInjection);

        //Router handler
        vertx.createHttpServer().requestHandler(router).listen(8000);
    }
    @Override
    public void stop() throws Exception{
        LOGGER.info("Ato @stop izao");
    }
    private void runInjection(RoutingContext routingContext){
        LOGGER.info("Check session....");
        this.sessionID=s.getSessionId();
        final JsonObject jsonResponse=new JsonObject();
        jsonResponse.put("sessionid",s.getSessionId());
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type","application/json")
                .end(Json.encodePrettily(jsonResponse));
    }
}
