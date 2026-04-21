package com.abdullaevaziz.fencingschoolfx.model;

import com.abdullaevaziz.fencingschoolfx.constants.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 4. TrainerSchedule – расписание тренера
 */
public class TrainerSchedule {

    private long id;


    @ToString.Exclude
    private Trainer trainer;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime mondayStart;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime mondayEnd;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime tuesdayStart;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime tuesdayEnd;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime wednesdayStart;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime wednesdayEnd;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime thursdayStart;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime thursdayEnd;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime fridayStart;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime fridayEnd;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime saturdayStart;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime saturdayEnd;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime sundayStart;


    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime sundayEnd;


    public List<TrainerScheduleItem> get() throws NoSuchFieldException, IllegalAccessException {
        List<TrainerScheduleItem> res = new ArrayList<>();
        for (String rusDay : Constants.DAYS.keySet()) {
            String engDay = Constants.DAYS.get(rusDay);
            LocalTime start = (LocalTime) this.getClass().getDeclaredField(engDay + "Start").get(this);
            LocalTime end = (LocalTime) this.getClass().getDeclaredField(engDay + "End").get(this);;
            if (start != null && end != null) {
                TrainerScheduleItem trainerScheduleItem = new TrainerScheduleItem(rusDay, engDay, start, end);
                res.add(trainerScheduleItem);
            }
        }
        return res;
    }

    public LocalTime[] get(String day) {
        try {
            LocalTime start = (LocalTime) this.getClass().getDeclaredField(day + "Start").get(this);
            LocalTime end = (LocalTime) this.getClass().getDeclaredField(day + "End").get(this);
            return new LocalTime[]{start, end};
        } catch (IllegalAccessException ignored) {
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Неверный формат дня недели get!");
        }
        return null;
    }

    public boolean isWorkMan(String day){
        LocalTime[] workHours = get(day);
        return  workHours[0] != null && workHours[1] != null;
    }

}
