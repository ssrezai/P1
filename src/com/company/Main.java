package com.company;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.company.exception.InvalidDepositTypeException;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvalidDepositTypeException, ClassNotFoundException {

        MyXMLHandler myXMLHandler = new MyXMLHandler();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        //user open his/her file!
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(new JPanel());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();
                saxParser.parse(selectedFile, myXMLHandler);
                //calculate interest rate
                for (int i = 0; i < myXMLHandler.getDepositList().size(); i++) {
                    myXMLHandler.getDepositList().get(i).CalculateInterest();
                }
                //sort final list!
                Collections.sort(myXMLHandler.getDepositList(), new InterestRateComparator());


            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            try {
                RandomAccessFile file = new RandomAccessFile(selectedFile.getParentFile().getAbsolutePath() + "/PayedInterestResult.txt", "rw");
                for (int i = 0; i < myXMLHandler.getDepositList().size(); i++) {
                    Deposit deposit = myXMLHandler.getDepositList().get(i);
                    file.writeBytes(deposit.getCustomerNumber() + "   #  " + deposit.getPayedInterest() + "\r\n");
                }
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
