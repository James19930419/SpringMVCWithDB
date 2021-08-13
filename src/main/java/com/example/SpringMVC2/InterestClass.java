package com.example.SpringMVC2;

public class InterestClass {
    private String custno;
    private double startAmonut;
    private double interest;


    private double endingBalance;
    public InterestClass(String custno, double startAmonut, double interest) {
        this.custno = custno;
        this.startAmonut = startAmonut;
        this.interest = interest;
    }

    public String getCustno() {
        return custno;
    }

    public void setCustno(String custno) {
        this.custno = custno;
    }

    public double getStartAmonut() {
        return startAmonut;
    }

    public void setStartAmonut(double startAmonut) {
        this.startAmonut = startAmonut;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(double endingBalance) {
        this.endingBalance = endingBalance;
    }
}
