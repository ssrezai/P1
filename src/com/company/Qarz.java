package com.company;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 * this class inheritance from DepositType
 */
public class Qarz extends DepositType {
    final private int INTEREST_RATE_VALUE;

    {
        INTEREST_RATE_VALUE = 0;
    }

    public Qarz() {
        setInterestRate();
    }

    public void setInterestRate() {
        super.setInterestRate(INTEREST_RATE_VALUE);
    }

}
