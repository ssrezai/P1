package com.company;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 *
 * @author : Samira Rezaei
 *         this class inheritance from DepositType
 */
public class LongTerm extends DepositType {
    final private int INTEREST_RATE_VALUE = 20;
    final private String TYPE_NAME = "LongTerm";


    public LongTerm() {
        super();
        setInterestRate();
    }

    public void setInterestRate() {
        super.setInterestRate(INTEREST_RATE_VALUE);
    }

    public void setTypeName() {
        super.setTypeName(TYPE_NAME);
    }

    public int getInterestRateValue() {
        return this.INTEREST_RATE_VALUE;
    }

}
