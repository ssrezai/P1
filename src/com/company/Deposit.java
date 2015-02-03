package com.company;

import com.company.ownexception.InvalidDepositTypeException;
import com.company.ownexception.InvalidDurationInDaysException;
import com.company.ownexception.NegativeBalanceException;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 * this class will be used for deposit.
 * have some fields such as customerNumber,depositBalance,...
 */
public class Deposit {
    private int customerNumber;
    private BigDecimal depositBalance;
    private BigDecimal payedInterest;
    private int durationInDays;
    private DepositType depositType;
    private String depositTypeName;

    //constructor
//    public Deposit(int customerNumber, BigDecimal depositBalance, BigDecimal payedInterest, int durationInDays, DepositType depositType, String depositTypeName) {
//        this.customerNumber = customerNumber;
//        this.depositBalance = depositBalance;
//        this.payedInterest = payedInterest;
//        this.durationInDays = durationInDays;
//        this.depositType = depositType;
//        this.depositTypeName = depositTypeName;
//    }
//    public Deposit(int aCustomerNumber, BigDecimal aDepositBalance,
//                   BigDecimal aPayedInterest, int aDurationInDays) {
//        this.customerNumber = aCustomerNumber;
//        this.depositBalance = aDepositBalance;
//        this.payedInterest = aPayedInterest;
//        this.durationInDays = aDurationInDays;
//    }

    public Deposit() {
    }


    //setter
    public void setPayedInterest(BigDecimal value) {
        this.payedInterest = value;
    }

    public void setCustomerNumber(int c_NUM) {
        this.customerNumber = c_NUM;
    }

    public void setDepositBalance(BigDecimal aDepositBalance) {
        this.depositBalance = aDepositBalance;
    }

    public void setdurationInDays(int adurationInDays) {
        this.durationInDays = adurationInDays;
    }

    public void setDepositType(DepositType aDepositType) {
        this.depositType = aDepositType;
    }

    public void setDepositTypeName(String name) {
        this.depositTypeName = name;
    }

    //getter methods
    public BigDecimal getPayedInterest() {
        return this.payedInterest;
    }

    public int getCustomerNumber() {
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

    public String getDepositTypeName() {
        return this.depositTypeName;
    }

    public Deposit madeReflectedObject(Deposit aDeposit, String aTypeName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class cls;
        DepositType obj;
        cls = Class.forName("com.company." + aTypeName);
        obj = (DepositType) cls.newInstance();
        this.setDepositType(obj);
        return aDeposit;
    }


    public BigDecimal payedInterest() {
        Checker checker = new Checker();
        BigDecimal pi;
        BigDecimal Db = this.getDepositBalance();
        try {
            if (!checker.checkDepositTypeName(this))
                throw new InvalidDepositTypeException("");

        } catch (InvalidDepositTypeException ex) {
            System.out.println("invalid type...");
        }
        try {
            if (!checker.checkDepositBalance(this.getDepositBalance()))
                throw new NegativeBalanceException("");
        } catch (NegativeBalanceException ex) {
            System.out.println("negative balance...");
        }
        try {
            if (!checker.checkDurationInDays(this.getDurationInDays()))
                throw new InvalidDurationInDaysException("");
        } catch (InvalidDurationInDaysException ex) {
            System.out.println("invalid duration in days...");
        }
        int days = this.getDurationInDays();

        pi = Db.multiply(new BigDecimal(this.getDepositType().getInterestRate()), MathContext.DECIMAL128).
                multiply(new BigDecimal(days), MathContext.DECIMAL128).divide(new BigDecimal(36500), MathContext.DECIMAL128);
        this.setPayedInterest(pi);
        return pi;
    }
}
