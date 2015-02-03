package com.company;

import java.util.Comparator;

/**
 * Created by DOTIN SCHOOL 3 on 2/3/2015.
 * @author Samira Rezaei
 * compare interest rate
 */
public class MyInterestRateComparetor implements Comparator<Deposit>
{
    @Override
    public int compare(Deposit object1,Deposit object2)
    {
        return  object1.getPayedInterest().compareTo(object2.getPayedInterest());
    }
}
