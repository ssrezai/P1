package com.company.ownexception;

/**
 * Created by DOTIN SCHOOL 3 on 2/1/2015.
 * exception handler for InvalidDepositType situations
 */
public class InvalidDepositTypeException extends Exception {
    public InvalidDepositTypeException(String msg) {
        super(msg);

    }
}