package com.company;

import com.company.exception.InvalidDepositTypeException;
import com.company.exception.InvalidDurationInDaysException;
import com.company.exception.NegativeBalanceException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by DOTIN SCHOOL 3 on 1/31/2015.
 *
 * @author Samira Rezaei
 *         in this class we parse your xml file
 */
public class MyXMLHandler extends DefaultHandler {
    private ArrayList<Deposit> depositList;

    private Deposit deposit;
    Validator validator = new Validator();
    boolean boolCustomerNumber = false;
    boolean boolDepositType = false;
    boolean boolDepositBalance = false;
    boolean boolDurationInDays = false;
    boolean checkedBeforeAdd = true;

    public ArrayList<Deposit> getDepositList() {
        return this.depositList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("deposit")) {
            checkedBeforeAdd = true;
            deposit = new Deposit();
            if (depositList == null)
                depositList = new ArrayList<Deposit>();
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
        if (elementName.equalsIgnoreCase("deposit") && checkedBeforeAdd) {
            depositList.add(deposit);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (boolCustomerNumber) {
            deposit.setCustomerNumber(new String(ch, start, length));
            boolCustomerNumber = false;
        }
        // in this part use Reflection for choose type of depositType in runTime!
        else if (boolDepositType) {
            String typeName = new String(ch, start, length);
            try {
                if (validator.validateDepositTypeName(typeName)) {
                    deposit.makeReflectedObject(deposit, typeName);
                }
            } catch (InvalidDepositTypeException ex) {
                checkedBeforeAdd = false;
                System.out.println("unknown deposit type for customer number: " + deposit.getCustomerNumber());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }

            //deposit.setDepositTypeName(typeName);
            boolDepositType = false;
        } else if (boolDepositBalance) {
            BigDecimal depositBalance = new BigDecimal(new String(ch, start, length));
            try {
                if (validator.validateDepositBalance(depositBalance)) {
                    deposit.setDepositBalance(depositBalance);
                }
            } catch (NegativeBalanceException ex) {
                checkedBeforeAdd = false;
                System.out.println("Negative deposit balance for customer number: " + deposit.getCustomerNumber());
            }

            boolDepositBalance = false;
        } else if (boolDurationInDays) {
            int days = Integer.parseInt(new String(ch, start, length));
            try {
                if (validator.validateDurationInDays(days)) {
                    deposit.setDurationInDays(days);
                }
            } catch (InvalidDurationInDaysException ex) {
                checkedBeforeAdd = false;
                System.out.println("invalid duration in days for customer number: " + deposit.getCustomerNumber());
            }

            boolDurationInDays = false;
        }
    }
}
