package com.company.ownexception;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 * * exception handler for NegativeBalance situations
 */
public class NegativeBalanceException extends Exception {
    public NegativeBalanceException(String msg) {
        super(msg);
    }
}
