package org.source.service;

public interface WSDAOInterface {
    public String getSessionID();
    public int getConnection(String access);
    public String injectData(String sessionID,String sessionAccess,String msisdn,String pass,String custommerMsisdn,String msg,int amount);
}
