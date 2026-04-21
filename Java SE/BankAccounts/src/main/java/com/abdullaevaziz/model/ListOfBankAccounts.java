package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ListOfBankAccounts {

    ArrayList<BankAccounts> bankAccountsArrayList = new ArrayList<>();

    @Override
    public String toString() {
        return "ListOfBankAccounts{" +
                "bankAccountsArrayList=" + bankAccountsArrayList +
                '}';
    }

    /**
     * Поиск по номеру счета
     *
     * @return
     */
    public BankAccounts searchAccountNumber(int numberAccount){
        for (BankAccounts bankAccounts : this.bankAccountsArrayList) {
            if(bankAccounts.getNumberAccounts() == numberAccount) {
                return bankAccounts;
            }
        }
        return null;
    }

    /**
     * Поиск владельца
     */
    public BankAccounts searchFio(String fio) {
        for (BankAccounts bankAccounts : this.bankAccountsArrayList) {
            if(bankAccounts.getFio().equals(fio)) {
                return bankAccounts;
            }
        }
        return null;
    }

    /**
     * Поиск по дате
     */
    public BankAccounts searchData(Date date) {
        for (BankAccounts bankAccounts : this.bankAccountsArrayList) {
            if(bankAccounts.getDataDay().equals(date)) {
                return bankAccounts;
            }
        }
        return null;
    }

    /**
     * Добавлять
     */
    public void add(BankAccounts bankAccounts){
        this.bankAccountsArrayList.add(bankAccounts);
    }

    /**
     * Сортировка
     */
    public void sort(Comparator<BankAccounts> bankAccountsComparator1){
        this.bankAccountsArrayList.sort(bankAccountsComparator1);
    }



}
