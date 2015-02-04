package com.company;

import com.company.exception.InvalidDepositTypeException;
import com.company.exception.InvalidDurationInDaysException;
import com.company.exception.NegativeBalanceException;

import java.math.BigDecimal;

/**
 * Created by DOTIN SCHOOL 3 on 2/3/2015.
 *
 * @author :Samira Rezaei
 *         this class check some wrong situations.
 */
public class Validator {
    public static boolean validateDepositTypeName(String type) throws InvalidDepositTypeException {
        if (type.equals("Qarz") || type.equals("LongTerm") || type.equals("ShortTerm")) {
            return true;
        } else {
            throw new InvalidDepositTypeException("");
        }
    }


    public static boolean validateDepositBalance(BigDecimal value) throws NegativeBalanceException {
        if (value.compareTo(BigDecimal.ZERO) >= 0) {
            return true;
        } else {
            throw new NegativeBalanceException("");
        }
    }

    public static boolean validateDurationInDays(int days) throws InvalidDurationInDaysException {
        if (days > 0) {
            return true;
        } else
        {

            throw new InvalidDurationInDaysException("");
        }
    }


}
