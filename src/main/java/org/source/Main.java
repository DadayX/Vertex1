package org.source;

import io.vertx.core.Vertx;
import org.source.controller.SessionVerticle;
import org.source.service.wsdl.SessionWS;
import org.source.service.wsdl.SessionWS_Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("App....");
        
        final Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(new SessionVerticle());
    }
}