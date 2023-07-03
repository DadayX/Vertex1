package org.source.service;

public class WSDAO implements WSDAOInterface{
    private WShandler handler;
    public WSDAO(){
        this.handler=new WShandler();
    }
    @Override
    public String getSessionID() {
        return this.handler.connectTOEndpoint().createSession();
    }

    @Override
    public int getConnection(String access) {
        return this.handler.connectTOEndpoint().getAccess(access);
    }

    @Override
    public String injectData(String sessionID,String sessionAccess,String msisdn,String pass,String custommerMsisdn,String msg,int amount) {
        return this.handler.connectTOEndpoint().injectData(sessionID,sessionAccess,msisdn,pass,custommerMsisdn,msg,amount);
    }
}
