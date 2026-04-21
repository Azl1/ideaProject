package com.abdullaevaziz.program;

import com.abdullaevaziz.model.BankAccounts;
import com.abdullaevaziz.model.ListOfBankAccounts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = dateFormat.parse("15.12.1999");
        Date date2 = dateFormat.parse("20.11.2000");
        Date date3 = dateFormat.parse("31.12.2008");


        BankAccounts bankAccounts1 = new BankAccounts(17134, 124, "Петров", 1000000, date1, 23);
        BankAccounts bankAccounts2 = new BankAccounts(12345, 123, "Иванов", 1555000, date3, 21);
        BankAccounts bankAccounts3 = new BankAccounts(67898, 528, "Сидоров", 1000000, date3, 17);
        BankAccounts bankAccounts4 = new BankAccounts(10105, 788, "Сидоров", 700000, date2, 16);


        /**
         * Компаратор дата открытия счета
         */
        if(bankAccounts2.compareTo(bankAccounts3) < 0){
            System.out.println("bankAccounts2 < bankAccounts3");
        }
        else if(bankAccounts2.compareTo(bankAccounts3) > 0){
            System.out.println("bankAccounts2 > bankAccounts3");
        }
        else{
            System.out.println("bankAccounts2 = bankAccounts3");
        }

        /**
         * Компаратор фамилия владельца.
         */
        Comparator<BankAccounts> bankAccountsComparator = new Comparator<BankAccounts>() {
            @Override
            public int compare(BankAccounts o1, BankAccounts o2) {
                return o1.getFio().compareTo(o2.getFio());
            }
        };
        if(bankAccountsComparator.compare(bankAccounts3, bankAccounts4) < 0){
            System.out.println("bankAccounts3 < bankAccounts4");
        }
        else if(bankAccountsComparator.compare(bankAccounts3, bankAccounts4) > 0){
            System.out.println("bankAccounts3 > bankAccounts4");
        }
        else{
            System.out.println("bankAccounts3 = bankAccounts4");
        }

        /**
         * Компаратор номер счета
         */
        Comparator<BankAccounts> bankAccountsComparator1 = new Comparator<BankAccounts>() {
            @Override
            public int compare(BankAccounts o1, BankAccounts o2) {
                if(o1.getNumberAccounts() == o2.getNumberAccounts()) {
                    if(o1.getDataDay() == o2.getDataDay()){
                        if (o1.getFio().equals(o2.getFio())) {
                            return o1.getFio().compareTo(o2.getFio());
                        }
                    }
                    return o1.getDataDay().compareTo(o2.getDataDay());
                }
                return Integer.compare(o1.getNumberAccounts(), o2.getNumberAccounts());
            }
        };
        if(bankAccountsComparator1.compare(bankAccounts1, bankAccounts2) < 0){
            System.out.println("bankAccounts1 < bankAccounts2");
        }
        else if(bankAccountsComparator1.compare(bankAccounts1, bankAccounts2) > 0){
            System.out.println("bankAccounts1 > bankAccounts2");
        }
        else{
            System.out.println("bankAccounts1 = bankAccounts2");
        }

        /**
         * Компаратор код счета
         */
        Comparator<BankAccounts> bankAccountsComparator2 = new Comparator<BankAccounts>() {
            @Override
            public int compare(BankAccounts o1, BankAccounts o2) {
                return Integer.compare(o1.getKodAccounts(), o2.getKodAccounts());
            }
        };
        if(bankAccountsComparator1.compare(bankAccounts1, bankAccounts2) < 0){
            System.out.println("bankAccounts1 < bankAccounts2");
        }
        else if(bankAccountsComparator1.compare(bankAccounts1, bankAccounts2) > 0){
            System.out.println("bankAccounts1 > bankAccounts2");
        }
        else{
            System.out.println("bankAccounts1 = bankAccounts2");
        }
        /**
         * Компаратор сумма на счете
         */
        Comparator<BankAccounts> bankAccountsComparator3 = new Comparator<BankAccounts>() {
            @Override
            public int compare(BankAccounts o1, BankAccounts o2) {
                return Integer.compare(o1.getAccounts(), o2.getAccounts());
            }
        };
        if(bankAccountsComparator1.compare(bankAccounts1, bankAccounts2) < 0){
            System.out.println("bankAccounts1 < bankAccounts2");
        }
        else if(bankAccountsComparator1.compare(bankAccounts1, bankAccounts2) > 0){
            System.out.println("bankAccounts1 > bankAccounts2");
        }
        else{
            System.out.println("bankAccounts1 = bankAccounts2");
        }

        /**
         * Компаратор годовой процент начисления
         */
        Comparator<BankAccounts> bankAccountsComparator4 = new Comparator<BankAccounts>() {
            @Override
            public int compare(BankAccounts o1, BankAccounts o2) {
                return Integer.compare(o1.getAnnualInterestAccrual(), o2.getAnnualInterestAccrual());
            }
        };
        if(bankAccountsComparator1.compare(bankAccounts1, bankAccounts2) < 0){
            System.out.println("bankAccounts1 < bankAccounts2");
        }
        else if(bankAccountsComparator1.compare(bankAccounts1, bankAccounts2) > 0){
            System.out.println("bankAccounts1 > bankAccounts2");
        }
        else{
            System.out.println("bankAccounts1 = bankAccounts2");
        }

        ListOfBankAccounts listAccount = new ListOfBankAccounts();
        listAccount.add(bankAccounts1);
        listAccount.add(bankAccounts2);
        System.out.println();
        listAccount.sort(null);
        System.out.println(listAccount);
        listAccount.sort(bankAccountsComparator1);
        System.out.println(listAccount);
        listAccount.sort(bankAccountsComparator2);
        System.out.println(listAccount);
        listAccount.sort(bankAccountsComparator3);
        System.out.println(listAccount);
        listAccount.sort(bankAccountsComparator4);
        System.out.println(listAccount);




    }
}