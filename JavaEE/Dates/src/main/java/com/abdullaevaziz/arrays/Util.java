package com.abdullaevaziz.arrays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Util {

    /**
     * Написать метод в классе Util,
     * который принимает массив и возвращает список из 2 масивов: даты которые позже
     * и даты которые раньше заданной даты во втором параметре метода
     */
    public List<LocalDate[]> listMass(LocalDate[] dates, LocalDate referenceDate) {

        List<LocalDate> before = new ArrayList<>();
        List<LocalDate> after = new ArrayList<>();
        for (LocalDate localDate : dates) {
            if (localDate.isBefore(referenceDate))
                before.add(localDate);
            if (localDate.isAfter(referenceDate))
                after.add(localDate);
        }

        LocalDate[] beforeMass = before.toArray(new LocalDate[0]);
        LocalDate[] afterMass = after.toArray(new LocalDate[0]);

        return Arrays.asList(beforeMass,afterMass);
    }

}
