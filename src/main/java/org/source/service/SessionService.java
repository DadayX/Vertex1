package org.source.service;

import org.source.entity.SessionApp;

public class SessionService {
    private SessionApp sessionApp;
    public String getSessionId(){
        sessionApp=new SessionApp("0123456789");
        return sessionApp.getId();
    }
}
