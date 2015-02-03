package com.company;

import com.company.ownexception.InvalidDepositTypeException;
import com.company.ownexception.InvalidDurationInDaysException;
import com.company.ownexception.NegativeBalanceException;

import java.math.BigDecimal;

/**
 * Created by DOTIN SCHOOL 3 on 2/3/2015.
 *
 * @author :Samira Rezaei
 *         this class check some wrong situations.
 */
public class Checker {
    public boolean checkDepositTypeName(String type) throws InvalidDepositTypeException {
        if (type.equals("Qarz") || type.equals("LongTerm") || type.equals("ShortTerm")) {
            return true;
        } else {
            throw new InvalidDepositTypeException("");
        }
    }

    public boolean checkDepositTypeName(Deposit deposit) throws InvalidDepositTypeException {
        if (deposit.getDepositTypeName().equals("Qarz") || deposit.getDepositTypeName().equals("LongTerm") || deposit.getDepositTypeName().equals("ShortTerm"))
            return true;
        else
            throw new InvalidDepositTypeException("");
    }

    public boolean checkDepositBalance(BigDecimal value) throws NegativeBalanceException {
        if (value.compareTo(BigDecimal.ZERO) >= 0) {
            return true;
        } else
            throw new NegativeBalanceException("");
    }

    public boolean checkDurationInDays(int days) throws InvalidDurationInDaysException {
        if (days > 0) {
            return true;
        } else
            throw new InvalidDurationInDaysException("");
    }


}
