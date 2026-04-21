package com.abdullaevaziz.main;

import com.abdullaevaziz.exception.InsufficientFundsException;
import com.abdullaevaziz.model.*;
import com.abdullaevaziz.repository.BankRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InsufficientFundsException, IOException {

         /*Json2PojoGenerator generator = new Json2PojoGenerator("bankJson.json","src/main/java/");
            generator.generate("Bank", "com.abdullaevaziz.modelJson");*/
        /*BankRepository bankRepository = new BankRepository();
        System.out.println(bankRepository);

        AccountList accountList1 = new AccountList();
        AccountList accountList2 = new AccountList();
        Bank bank = new Bank();

        Account account1 = new Account("898456ABC", "Иванов И.И", new BigDecimal(734345));
        Account account2 = new Account("274456BCA", "Петров П.П", new BigDecimal(1000345));
        Account account3 = new Account("7388456FFF", "Березин Ф.Р", new BigDecimal(7000000));
        Account account4 = new Account("34456GGG", "Сидоров С.С", new BigDecimal(1000));
        Account account5 = new Account("77725456LLL", "Букин Б.Б", new BigDecimal(4345));

        Account account6 = new Account("54456DBC", "Волков А.С", new BigDecimal(8634348));
        Account account7 = new Account("27867744WCA", "Петрушкин В.С", new BigDecimal(57345));
        Account account8 = new Account("1238456FTI", "Волков А.С", new BigDecimal(575000));
        Account account9 = new Account("378GVB", "Романов Н.М", new BigDecimal(157000));
        Account account10 = new Account("74545456QFL", "Рудьков В.В", new BigDecimal(74345));

        System.out.println("---Аккаунт-------------------------------------------");
        System.out.println("Увеличение баланса аккаунта на заданную величину");
        BigDecimal bigDecimal1 = account1.balanceIncrease(new BigDecimal(5454));
        System.out.println(bigDecimal1);
        System.out.println("Уменьшение баланса аккаунта на заданную величину");
        BigDecimal bigDecimal2 = account2.decreaseBalance(new BigDecimal(5454));
        System.out.println(bigDecimal2);
        System.out.println("Перевода суммы другому лицу");
        account3.transfer(account4, new BigDecimal(555));


        System.out.println("---Список аккаунтов--------------------------------------------");
        System.out.println("Добавление пользователя в список аккаунтов");
        accountList1.userAdd(account1);
        accountList1.userAdd(account2);
        accountList1.userAdd(account3);
        accountList1.userAdd(account4);
        accountList1.userAdd(account5);

        accountList2.userAdd(account6);
        accountList2.userAdd(account7);
        accountList2.userAdd(account8);
        accountList2.userAdd(account9);
        accountList2.userAdd(account10);

        System.out.println("Удаление аккаунта из списка аккаунтов");
        accountList1.removeAccount(account5);
        System.out.println("Получение первого аккаунта");
        Account accountFirst = accountList1.gettingFirstAccount();
        System.out.println(accountFirst);
        System.out.println("Получение последнего аккаунта");
        Account accountLast = accountList1.gettingLastAccount();
        System.out.println(accountLast);
        System.out.println("Получение аккаунта по индексу");
        int resIndex = accountList1.indexAccount(account2);
        System.out.println(resIndex);
        System.out.println("Проверки на существование аккаунта в списке");
        boolean isExistenceAccountList = accountList1.isExistenceAccountList(account3);
        System.out.println(isExistenceAccountList);
        System.out.println("Получение количества аккаунтов");
        int resSize = accountList1.getSizeOfAccounts();
        System.out.println(resSize);
        System.out.println("Получение аккаунта по его номеру");
        Account res1 = accountList1.getAccountByNumber("274456BCA");
        System.out.println(res1);

        System.out.println("---Банк---------------------------------------");
        Account accountNew1 = bank.createNewAccount("565EWQ", "Рябкин Ю.Л", new BigDecimal(446346));
        Account accountNew2 = bank.createNewAccount("788TYY", "Арбузов К.О", new BigDecimal(742757));
        Account accountNew3 = bank.createNewAccount("378BBB", "Трусов П.Я", new BigDecimal(2173852));
        Account accountNew4 = bank.createNewAccount("878AAA", "Ромашкин В.Д", new BigDecimal(500000));
        System.out.println(accountNew1);
        System.out.println(accountNew2);
        System.out.println(accountNew3);
        System.out.println("Добавление аккаунта в банк");
        bank.addAccountInBank(accountNew1);
        bank.addAccountInBank(accountNew2);
        bank.addAccountInBank(accountNew3);
        System.out.println("Удаление аккаунта из банка");
        bank.removeAccountInBank(accountNew4);
        System.out.println("Проверки на существование аккаунта в банке");
        boolean isExistenceAccount = bank.existenceAccount(accountNew3);
        System.out.println(isExistenceAccount);
        System.out.println("Внутрибанковского перевода сумм между счетами");
        Account fromAccount = bank.intraBankTransfer("788TYY", "565EWQ", new BigDecimal(500));
        System.out.println(fromAccount);
        Bank bank1 = new Bank(587656);
        Bank bank2 = new Bank(788787);
        System.out.println(bank1);
        System.out.println(bank2);

        System.out.println("---Банк репозиторий---------------------------------------");
        BankRepository bankRepository1 = new BankRepository(bank1);
        BankRepository bankRepository2 = new BankRepository("bankJson.json");
        System.out.println(bankRepository2);
        try {
            bankRepository1.saveBank("bankJson.json");
            bankRepository1.removeBank("587656");
            System.out.println(bankRepository1.getLoadedBank());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bankRepository1);*/



        /*Bank bank = new Bank(1000);
        Account accountNew1 = bank.createNewAccount("565EWQ", "Рябкин Ю.Л", new BigDecimal(10000));
        Account accountNew2 = bank.createNewAccount("788TYY", "Арбузов К.О", new BigDecimal(742757));
        Account accountNew3 = bank.createNewAccount("378BBB", "Трусов П.Я", new BigDecimal(2173852));
        Account accountNew4 = bank.createNewAccount("878AAA", "Ромашкин В.Д", new BigDecimal(500000));
        Account accountNew5 = bank.createNewAccount("855AZ", "Варюшкин П.П", new BigDecimal(80000));

        Account account6 = bank.createNewAccount("54456DBC", "Волков А.С", new BigDecimal(8634348));
        Account account7 = bank.createNewAccount("27867744WCA", "Петрушкин В.С", new BigDecimal(57345));
        Account account8 = bank.createNewAccount("1238456FTI", "Волков А.С", new BigDecimal(575000));
        Account account9 = bank.createNewAccount("378GVB", "Романов Н.М", new BigDecimal(157000));
        Account account10 = bank.createNewAccount("74545456QFL", "Рудьков В.В", new BigDecimal(74345));

        bank.addAccountInBank(accountNew1);
        bank.addAccountInBank(accountNew2);
        bank.addAccountInBank(accountNew3);
        bank.addAccountInBank(accountNew4);
        bank.addAccountInBank(accountNew5);

        bank.addAccountInBank(account6);
        bank.addAccountInBank(account7);
        bank.addAccountInBank(account8);
        bank.addAccountInBank(account9);
        bank.addAccountInBank(account10);

        BankRepository bankRepository = new BankRepository(bank);
        try {
            bankRepository.saveBank("SuperBankJson.json");
            bankRepository.updateBankData(bank,"SuperBankJson2.json");
            bankRepository.removeBank("SuperBankJson.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        /*BankRepository bankRepository = new BankRepository("SuperBankJson2.json");
        Bank newBank = bankRepository.getLoadedBank();
        System.out.println(newBank);

        Scanner scanner = new Scanner(System.in);
        String account1 = scanner.nextLine();
        String account2 = scanner.nextLine();
        BigDecimal sum = scanner.nextBigDecimal();
        try {
            Account accountRes = newBank.intraBankTransfer(account1, account2, sum);
            System.out.println(accountRes);
        } catch (InsufficientFundsException insufficientFundsException){
            System.out.println(insufficientFundsException.getMessage());
        }*/
        Scanner scanner = new Scanner(System.in);
        Bank bank = null;
        while (true) {
            System.out.println("1-создать новый банк\n2-загрузить банк из файла\n3-Cохранить банк в базу данных" +
                    "\n4-Удаление банка из базы данных" +
                    "\n------------------------------------------------------------" +
                    "\n5-создание нового аккаунта\n6-добавление аккаунта в банк" +
                    "\n7-Удаление аккаунта из банка\n8-Проверки на существование аккаунта в банке" +
                    "\n9-Внутрибанковского перевода сумм между счетами \n10-Выход");

            int select = scanner.nextInt();
            if (select == 1) {
                System.out.println("Введите код для создания номера банка");
                int kod = scanner.nextInt();
                bank = new Bank(kod);
                System.out.println(bank + " создан\n");
            } else if (select == 2) {
                try {
                    System.out.println("Введите имя файла");
                    String fileName = scanner.next();
                    BankRepository bankRepository = new BankRepository(fileName);
                    bank = bankRepository.getBank();
                    System.out.println(bank);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Ошибка загрузки из файла!");
                }
            } else if (select == 3) {
                if (bank == null) {
                    System.out.println("Сначала выберите 1 или 2 опцию");
                    continue;
                }
                try {
                    String fileName = scanner.next();
                    BankRepository bankRepository = new BankRepository(bank);
                    bankRepository.saveBank(fileName);
                    System.out.println("Банк сохранен в базу\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Ошибка сохранения файла!");
                }
            } else if (select == 4) {
                if (bank == null) {
                    System.out.println("Сначала выберите 1 или 2 опцию");
                    continue;
                }
                try {
                    String fileName = scanner.next();
                    BankRepository bankRepository = new BankRepository(fileName);
                    bankRepository.removeBank(fileName);
                    System.out.println("Банк удален из базы\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Ошибка удаления из базы!");
                }
            } else if (select == 5) {
                if (bank == null) {
                    System.out.println("Сначала выберите 1 или 2 опцию");
                    continue;
                }
                System.out.println("Введите номер аккаунта");
                String numberAccount = scanner.next();
                System.out.println("Введите ФИО");
                String FIO = scanner.next();
                System.out.println("Введите сумму");
                BigDecimal balance = scanner.nextBigDecimal();
                Account account1 = bank.createNewAccount(numberAccount, FIO, balance);
                System.out.println("Аккаунт создан " + account1 + "\n");
            } else if (select == 6) {
                if (bank == null) {
                    System.out.println("Сначала выберите 1 или 2 опцию");
                    continue;
                }
                System.out.println("Введите номер аккаунта");
                String numberAccount = scanner.next();
                System.out.println("Введите ФИО");
                String FIO = scanner.next();
                System.out.println("Введите сумму");
                BigDecimal balance = scanner.nextBigDecimal();
                Account account1 = new Account(numberAccount, FIO, balance);
                boolean isAdd = bank.addAccountInBank(account1);
                if (isAdd) {
                    System.out.println("\nАккаунт добавлен \n" + true);
                } else {
                    System.out.println("\nАккаунт не добавлен \n" + false);
                }
            } else if (select == 7) {
                if (bank == null) {
                    System.out.println("Сначала выберите 1 или 2 опцию");
                    continue;
                }
                System.out.println("Введите номер аккаунта");
                String numberAccount = scanner.next();
                Account accountGet = bank.getAccount(numberAccount);
                if (accountGet == null) {
                    System.out.println("Аккаунт отсутствует, удаление невозможно!");
                    continue;
                }
                boolean isDelete = bank.removeAccountInBank(accountGet);
                if (isDelete) {
                    System.out.println("\nАккаунт удален \n" + true);
                } else {
                    System.out.println("\nАккаунт не удален \n" + false);
                }
            } else if (select == 8) {
                if (bank == null) {
                    System.out.println("Сначала выберите 1 или 2 опцию");
                    continue;
                }
                System.out.println("Введите номер аккаунта");
                String numberAccount = scanner.next();
                System.out.println("Введите ФИО");
                String FIO = scanner.next();
                System.out.println("Введите сумму");
                BigDecimal balance = scanner.nextBigDecimal();
                Account account1 = bank.createNewAccount(numberAccount, FIO, balance);
                boolean isExistenceAccount = bank.existenceAccount(account1);
                if (isExistenceAccount) {
                    System.out.println("\nАккаунт в наличии: \n" + true);
                } else {
                    System.out.println("\nАккаунт отсутствует: \n" + false);
                }
            } else if (select == 9) {
                if (bank == null) {
                    System.out.println("Сначала выберите 1 или 2 опцию");
                    continue;
                }
                System.out.println("Введите данные аккаунта откуда");
                String fromAccountNumber = scanner.next();
                System.out.println("Введите данные аккаунта куда");
                String toAccountNumber = scanner.next();
                System.out.println("Введите сумму перевода");
                BigDecimal balance = scanner.nextBigDecimal();
                try {
                    Account accountTransit = bank.intraBankTransfer(fromAccountNumber, toAccountNumber, balance);
                    System.out.println("\nВнутрибанковского перевода сумм между счетами успешно завершен" + accountTransit + "\n");
                } catch (InsufficientFundsException e) {
                    System.out.println("Внимание! " + e.getMessage());
                }
            } else if (select == 10) {
                System.out.println("Вы вышли из меню");
                return;
            }
        }

    }
}