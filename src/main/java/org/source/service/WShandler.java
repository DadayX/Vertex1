package org.source.service;

import org.source.service.wsdl.SessionWS;
import org.source.service.wsdl.SessionWS_Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class WShandler {
    private WSConsume ws;
    public WShandler(){
        this.ws=new WSConsume();
    }
    public SessionWS connectTOEndpoint(){
        final String endpoint=this.ws.getUri();
        final URL url;
        try {
            url = URI.create(endpoint).toURL();
            final SessionWS_Service service=new SessionWS_Service(url);
            //System.out.println(service.getSessionWSPort().createSession());
            return service.getSessionWSPort();
        } catch (MalformedURLException e) {
           // throw new RuntimeException(e);
            return null;
        }
    }

}
