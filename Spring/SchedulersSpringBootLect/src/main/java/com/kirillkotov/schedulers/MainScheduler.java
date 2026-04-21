package com.kirillkotov.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class MainScheduler {
    private static final Logger log = LoggerFactory.getLogger(MainScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        log.info("Scheduled at: ", dateFormat.format(new Date()));
        System.out.println("Da zdravstvuyet Lenin! Терпенье и Труд все перетруТ!!!");
    }
}
