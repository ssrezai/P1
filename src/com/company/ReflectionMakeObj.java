package com.company;

import com.company.ownexception.InvalidDepositTypeException;

import java.util.ArrayList;

/**
 * Created by DOTIN SCHOOL 3 on 2/2/2015.
 * this class designed for...
 * atrib:...
 */
public class ReflectionMakeObj {
    public ArrayList<Deposit> reflectionMade(MyXMLHandler parsedList) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvalidDepositTypeException {
        ArrayList<Deposit> finalList = new ArrayList<Deposit>();
        ArrayList<Deposit> firstList = parsedList.getUsers();
        int counterValidUser = 0;
        for (int i = 0; i < parsedList.getUsers().size(); i++)
        {
            try
            {
            if (firstList.get(i).checkedType())
            {
                Class cls = null;
                DepositType obj = null;
                cls = Class.forName("com.company." + firstList.get(i).getDepositTypeName());
                obj = (DepositType) cls.newInstance();
                Deposit tempDeposit = firstList.get(i);
                tempDeposit.setDepositType(obj);
                tempDeposit.getDepositType().setTypeName(firstList.get(i).getDepositTypeName());

                if (finalList != null) {
                    finalList.add(counterValidUser, tempDeposit);
                    counterValidUser++;
                }
                tempDeposit = null;
            }
            else
                throw new InvalidDepositTypeException("" );

            }
            catch (InvalidDepositTypeException ex)
            {
                System.out.println("Unknown Deposit Type ("+firstList.get(i).getDepositTypeName()+")...for customre#: "+firstList.get(i).getCustomerNumber());
            }
            //else {
               // throw new InvalidDepositTypeException("invalid depositTypeName for customer number" + finalList.get(i).getCustomerNumber());

            //}
            continue;
        }
        return finalList;
    }
}
