package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerScheduleItem {
    private String rusDay;
    private String engDay;
    private LocalTime start;
    private LocalTime end;
}
