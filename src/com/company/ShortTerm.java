package com.company;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 *
 * @author : Samira Rezaei
 *         this class inheritance from DepositType
 */
public class ShortTerm extends DepositType {
    final private int INTEREST_RATE_VALUE;

    {
        INTEREST_RATE_VALUE = 10;
    }

    public ShortTerm() {
        super();
        setInterestRate();
    }

    public void setInterestRate() {
        super.setInterestRate(INTEREST_RATE_VALUE);
    }

}
