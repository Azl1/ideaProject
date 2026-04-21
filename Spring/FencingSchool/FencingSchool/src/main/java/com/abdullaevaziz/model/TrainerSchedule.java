package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Trainer_schedules")
/**
 * 4. TrainerScheduleService – расписание тренера
 */
public class TrainerSchedule {

    @Id
    private long id;

    @NonNull
    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @MapsId
    @JoinColumn(name = "trainer_id")
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

    public void set(String day, LocalTime start, LocalTime end) {
        try {
            this.getClass().getDeclaredField(day + "Start").set(this, start);
            this.getClass().getDeclaredField(day + "End").set(this, end);

        } catch (IllegalAccessException ignored) {
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Неверный формат дня недели!");
        }
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
