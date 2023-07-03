package org.source.entity;

public class Transaction {
    private String customerMsisdn;
    private String msg;
    private int amount;
    private int res;

    public Transaction() {

    }

    public Transaction(String customerMsisdn, String msg, int amount, int res) {
        this.customerMsisdn = customerMsisdn;
        this.msg = msg;
        this.amount = amount;
        this.res = res;
    }

    public String getCustomerMsisdn() {
        return customerMsisdn;
    }

    public void setCustomerMsisdn(String customerMsisdn) {
        this.customerMsisdn = customerMsisdn;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
