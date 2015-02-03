package com.company;

import java.math.BigDecimal;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 * @author : Samira Rezaei
 * this class inheritance from DepositType
 */
public class ShortTerm extends DepositType
{
    final private int INTEREST_RATE_VALUE = 10;
    final private String TYPE_NAME="ShortTerm";

    public ShortTerm()
    {
        super();
        setInterestRate();
    }
    public int getInterestRateValue()
    {
        return this.INTEREST_RATE_VALUE;
    }
    public void setInterestRate()
    {
        super.setInterestRate(INTEREST_RATE_VALUE);
    }
    public void setTypeName()
    {
        super.setTypeName(TYPE_NAME);
    }
}
