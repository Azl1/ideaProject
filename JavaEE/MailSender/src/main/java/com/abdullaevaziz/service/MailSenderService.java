package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Letter;
import com.abdullaevaziz.util.MailSender;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MailSenderService {

    private LinkedBlockingQueue<Letter> letters = new LinkedBlockingQueue<>();

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            /**
             * • отправку из начала очереди письма заданному списку адресов,
             * каждому новому адресату производить отправку в новом потоке
             */
            try {
                while (true) {
                    /**
                     * • ожидание нового письма в очереди
                     */
                    Letter letter = letters.take();
                    List<String> letterArrayList = letter.getUserArrayList();

                    int countAddress = letterArrayList.size();
                    ExecutorService executorService1 = Executors.newFixedThreadPool(countAddress);
                    for (String email : letterArrayList) {
                        executorService1.submit(() -> {
                            /**
                             * • отправку производить с использованием класса из проекта Tables
                             */
                            String accountFrom = "twe12345@bk.ru";
                            String password = "6DMvP77QH4WziMA1rvuN";
                            MailSender mailSender = new MailSender(accountFrom, password, email);
                            mailSender.send(letter.getTopicLetter(), letter.getTextLetter());
                        });
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    });

    public MailSenderService() {
        thread.start();
    }

    /**
     * • производим добавление нового письма в очередь на отправку
     */
    public void add(Letter letter) {
        this.letters.add(letter);
    }
}
