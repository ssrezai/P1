package com.company;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 * @author Samira Rezaei
 */
public abstract class DepositType
{
    private int interestRate;
    //private String typeName;
    public DepositType()
    {}

    public int getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(int value)
    {
        this.interestRate=value;
    }
//    public String getTypeName()
//    {
//        return  this.typeName;
//    }
//    public  void setTypeName(String name)
//    {
//        this.typeName=name;
//    }
}
