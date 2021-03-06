package com.company;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 *
 * @author : Samira Rezaei
 *         this class inheritance from DepositType
 */
public class LongTerm extends DepositType {
    final private int INTEREST_RATE_VALUE = 20;

//    {
//        INTEREST_RATE_VALUE = 20;
//    }

    public LongTerm() {
        super();
        setInterestRate();
    }

    public void setInterestRate() {
        super.setInterestRate(INTEREST_RATE_VALUE);
    }


}
