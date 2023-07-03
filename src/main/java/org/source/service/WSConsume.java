package org.source.service;

import org.source.service.wsdl.SessionWS_Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class WSConsume {
    public WSConsume(String uri) {
        Uri = uri;
    }

    public WSConsume() {
        this.Uri="http://localhost:8080/SessionWS/SessionWS?WSDL";
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    private String Uri;

}
