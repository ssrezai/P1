package com.company;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.company.ownexception.InvalidDepositTypeException;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvalidDepositTypeException, ClassNotFoundException {

        MyXMLHandler myXMLHandler = new MyXMLHandler();
        //MyXMLHandler myXMLHandler2 = new MyXMLHandler();
        SAXParserFactory spf = SAXParserFactory.newInstance();

        //user open his/her file!
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(new JPanel());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                SAXParser sp = spf.newSAXParser();
                sp.parse(selectedFile, myXMLHandler);
                //calculate interest rate
                for (int i = 0; i < myXMLHandler.getUsers().size(); i++) {
                    myXMLHandler.getUsers().get(i).payedInterest();
                    //System.out.println(myXMLHandler.getUsers().get(i).payedInterest());
                }
                //sort final list!
                Collections.sort(myXMLHandler.getUsers(), new MyInterestRateComparetor());

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            try {
                File OutputFile = new File(selectedFile.getParentFile().getAbsolutePath() + "/output.txt");
                if (!OutputFile.exists()) {
                    OutputFile.createNewFile();
                }

                FileWriter fileWriter = new FileWriter(OutputFile.getAbsoluteFile());
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (int i = 0; i < myXMLHandler.getUsers().size(); i++) {
                    Deposit deposit = myXMLHandler.getUsers().get(i);
                    bufferedWriter.write(deposit.getCustomerNumber() + " # " + deposit.getPayedInterest());
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
