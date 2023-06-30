package org.source.service;

import io.vertx.core.Vertx;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.source.entity.SessionApp;

public class SessionService {
    private SessionApp sessionApp;
    public String getSessionId(){
        Vertx vertx=Vertx.currentContext().owner();
        vertx.executeBlocking(t->{
            ClientProxyFactoryBean proxy=new ClientProxyFactoryBean();
            proxy.setAddress("http://localhost:8080/SessionWS/SessionWS");
            SessionServiceImpl session=proxy.create(SessionServiceImpl.class);
            System.out.println("========>"+session.createSession());
        }, false,result->{
            System.out.println("========>"+result.cause());
        });

        sessionApp=new SessionApp("0123456789");
        return sessionApp.getId();
    }
}
