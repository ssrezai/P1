package com.company;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 * @author Samira Rezaei
 */
public abstract class DepositType
{
    private int interestRate;

    public int getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(int value)
    {
        this.interestRate=value;
    }

}
