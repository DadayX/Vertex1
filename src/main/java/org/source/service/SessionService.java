package org.source.service;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.source.entity.SessionApp;
import org.source.entity.Transaction;

import java.io.*;

public class SessionService implements SessionServiceImpl{
    private SessionApp sessionApp;
    private Vertx vertx;
    private WSDAO dao;
    private String sessionAccessCode;
    private Transaction transaction;
    public SessionService(){
        this.dao=new WSDAO();
        this.sessionApp=new SessionApp();
        this.sessionApp.setId(searchFile().replace(".tmp",""));
        this.vertx=Vertx.currentContext().owner();
        this.sessionAccessCode="";
        this.transaction=null;
    }
    public String getSessionAccessCode(){
        String sessionId=this.sessionApp.getId();
        //Code converter
        String sessionAccess="baltazar7654321loginpassword";
        return sessionAccess;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getSessionId(){

        this.vertx.executeBlocking(t->{
            if(searchFile().isEmpty()){
                createSessionFile(this.dao.getSessionID());
            }
            String sessionId=searchFile().replace(".tmp","");
            this.sessionApp=new SessionApp(sessionId);
                //Convert cripted sessionID
            String sessionAccess=this.getSessionAccessCode();
            //connect session
            if(this.dao.getConnection(sessionAccess)==1){
                writeSessionAccess(new File(sessionId+".tmp"),sessionAccess);
            }

        }, false,result->{
            System.out.println("========>"+result.cause());
        });
        return this.sessionApp.getId();
    }
    public boolean createSessionFile(String session){
        File f =new File(session+".tmp");
        if(f.exists()){
            return false;
        }else{
            return createSessionFile(f.getAbsolutePath());
        }

    }
    public String searchFile(){
        String directory="./";
        File dir = new File(directory);
        FilenameFilter filter=new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".tmp");
            }
        };
        return dir.list()[0];
    }
    public boolean removeFile(String filename){
        File f=new File(filename);
        if(!f.exists()){
            return false;
        }
        f.deleteOnExit();
        return true;
    }
    public void writeSessionAccess(File f,String accessCode){
        String filename=f.getName().replace(".tmp","");
       /* this.vertx.fileSystem()
                .writeFile(f.getAbsolutePath(), Buffer.buffer(accessCode))
                .onComplete(result->{
                    if(result.succeeded()){
                        System.out.println("accessCode created...");

                    }else{
                        System.out.println("accessCode not exist...");
                    }
                });*/
        f.renameTo(new File(filename+"_"+accessCode+".tmp"));
        System.out.println("Create "+f.getName()+"file....");
    }

    @Override
    public String injectData(Transaction transaction) {
        String res="";
        this.transaction=transaction;
        this.vertx.executeBlocking(t->{
            if(searchFile().isEmpty()){
                this.sessionApp.setId(getSessionId());
            }
            String customerMsisdn=this.transaction.getCustomerMsisdn();
            String msg=this.transaction.getMsg();
            int amount=this.transaction.getAmount();
            if(this.dao.injectData(this.getSessionId(),this.getSessionAccessCode(),"0340000001","0000",customerMsisdn,msg,amount).equals("ok")){
                this.transaction.setRes(0);
            }else{
                this.transaction.setRes(1);
            }

        }, false,result->{
            System.out.println("========>"+result.cause());
        });
        return this.transaction.getRes()==0?"OK":"KO";
    }
}
