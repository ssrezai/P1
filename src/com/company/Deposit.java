package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 * this class will be used for deposit.
 * have some fields such as customerNumber,depositBalance,...
 */
public class Deposit {
    private String customerNumber;
    private BigDecimal depositBalance;
    private BigDecimal payedInterest;
    private int durationInDays;
    private DepositType depositType;


    //setter
    public void setPayedInterest(BigDecimal value) {
        this.payedInterest = value;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }


    //getter methods
    public BigDecimal getPayedInterest() {
        return this.payedInterest;
    }

    public String getCustomerNumber() {
        return this.customerNumber;
    }

    public BigDecimal getDepositBalance() {
        return this.depositBalance;
    }

    public int getDurationInDays() {
        return this.durationInDays;
    }

    public DepositType getDepositType() {
        return this.depositType;
    }

    //required methods
    public Deposit makeReflectedObject(Deposit deposit, String typeName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class depositTypeClass;
        DepositType depositType;
        depositTypeClass = Class.forName("com.company." + typeName);
        depositType = (DepositType) depositTypeClass.newInstance();
        this.setDepositType(depositType);
        return deposit;
    }

    public void calculatePayedInterest() {
        BigDecimal payedInterest;
        BigDecimal depositBalance = this.getDepositBalance();
        int days = this.getDurationInDays();
        //calculate
        payedInterest = depositBalance.multiply(new BigDecimal(this.getDepositType().getInterestRate())).
                multiply(new BigDecimal(days)).divide(new BigDecimal(36500), MathContext.DECIMAL128);
        this.setPayedInterest(payedInterest);
    }


}
