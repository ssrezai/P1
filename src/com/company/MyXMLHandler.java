package com.company;

import com.company.ownexception.InvalidDepositTypeException;
import com.company.ownexception.InvalidDurationInDaysException;
import com.company.ownexception.NegativeBalanceException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by DOTIN SCHOOL 3 on 1/31/2015.
 * @author Samira Rezaei
 * in this class we parse your xml file
 */
public class MyXMLHandler extends DefaultHandler {
    private ArrayList<Deposit> users;

    private Deposit deposit;
    Checker checker = new Checker();
    boolean boolCustomerNumber = false;
    boolean boolDepositType = false;
    boolean boolDepositBalance = false;
    boolean boolDurationInDays = false;
    boolean checkBeforeAdd = true;

    public ArrayList<Deposit> getUsers() {
        return this.users;
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("deposit")) {
            checkBeforeAdd = true;
            deposit = new Deposit();
            if (users == null)
                users = new ArrayList<Deposit>();
        } else if (qName.equalsIgnoreCase("customerNumber")) {
            boolCustomerNumber = true;
        } else if (qName.equalsIgnoreCase("depositType")) {
            boolDepositType = true;
        } else if (qName.equalsIgnoreCase("depositBalance")) {
            boolDepositBalance = true;
        } else if (qName.equalsIgnoreCase("durationInDays")) {
            boolDurationInDays = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String elementName) throws SAXException {
        if (elementName.equalsIgnoreCase("deposit") && checkBeforeAdd) {
            users.add(deposit);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (boolCustomerNumber) {
            String st = new String(ch, start, length);
            deposit.setCustomerNumber(Integer.parseInt(st));
            boolCustomerNumber = false;
        }
        // in this part use Reflection for choose type of depositType in runTime!
        else if (boolDepositType) {
            String typeName = new String(ch, start, length);
            try {

                if (checker.checkDepositTypeName(typeName)) {
                    try {
                        deposit.madeReflectedObject(deposit, typeName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }

            } catch (InvalidDepositTypeException ex) {
                checkBeforeAdd = false;
                System.out.println("unknown deposit type for customer number: " + deposit.getCustomerNumber());
            }
            deposit.setDepositTypeName(typeName);
            boolDepositType = false;
        } else if (boolDepositBalance) {
            BigDecimal depositBalance = new BigDecimal(new String(ch, start, length));
            try {
                if (checker.checkDepositBalance(depositBalance)) {
                    deposit.setDepositBalance(depositBalance);
                }
            } catch (NegativeBalanceException ex) {
                checkBeforeAdd = false;
                System.out.println("Negative deposit balance for customer number: " + deposit.getCustomerNumber());
            }

            boolDepositBalance = false;
        } else if (boolDurationInDays) {
            int days = Integer.parseInt(new String(ch, start, length));
            try {
                if (checker.checkDurationInDays(days)) {
                    deposit.setdurationInDays(days);
                }
            } catch (InvalidDurationInDaysException ex) {
                checkBeforeAdd = false;
                System.out.println("invalid duration in days for customer number: " + deposit.getCustomerNumber());
            }

            boolDurationInDays = false;
        }
    }
}
