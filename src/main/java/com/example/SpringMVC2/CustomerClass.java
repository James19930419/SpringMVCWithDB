package com.example.SpringMVC2;

public class CustomerClass {

    private String custno;
    private String custname;
    private Double cdep;
    private int nyears;
    private String savtype;

    public Double getCdep() {
        return cdep;
    }

    public void setCdep(Double cdep) {
        this.cdep = cdep;
    }

    public int getNyears() {
        return nyears;
    }

    public void setNyears(int nyears) {
        this.nyears = nyears;
    }

    public String getSavtype() {
        return savtype;
    }

    public void setSavtype(String savtype) {
        this.savtype = savtype;
    }

    public String getCustno() {
        return custno;
    }

    public void setCustno(String custno) {
        this.custno = custno;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public CustomerClass(String custno, String custname){
        this.custno = custno;
        this.custname = custname;
    }
    public CustomerClass(String custno, String custname, Double cdep, int nyears, String savtype) {
        this.custno = custno;
        this.custname = custname;
        this.cdep = cdep;
        this.nyears = nyears;
        this.savtype = savtype;
    }

}
