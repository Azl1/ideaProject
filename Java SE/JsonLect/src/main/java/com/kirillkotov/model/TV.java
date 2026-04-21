package com.kirillkotov.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @author Kotov Kirill
 * For generating data schema you can use Json2PojoGenerator class
 */
public class TV {
    private String brand;
    private String model;

    //@JsonIgnore /*Полное игнорирование поля*/
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) /*Поле будет только считываться из json*/
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY) /*Поле будет только записываться из json*/
    private String color;

    //@JsonAlias(value = {"Time Expectancy", "Time"}) /*Мягкий псевдоним*/
    //@JsonProperty(value = "Time Expectancy") /*Жесткий псевдоним*/
    private int timeExpectancy;
    private double price;

    //Формат даты
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date date = new Date();

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime localDateTime = LocalDateTime.now();
    /*@JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate localDate = LocalDate.now();*/
    /*@JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime localTime = LocalTime.now();*/

    private Size size = new Size();

    /**
     * Important!!! Default constructor, getters and setters are required in ObjectMapper JSON Parser
     */
    public TV() {
    }

    public TV(String brand, String model, String color, int timeExpectancy, double price,
              Date date, LocalDateTime localDateTime, Size size) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.timeExpectancy = timeExpectancy;
        this.price = price;
        this.date = date;
        this.localDateTime = localDateTime;
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTimeExpectancy() {
        return timeExpectancy;
    }

    public void setTimeExpectancy(int timeExpectancy) {
        this.timeExpectancy = timeExpectancy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TV tv = (TV) o;
        return timeExpectancy == tv.timeExpectancy && Double.compare(tv.price, price) == 0
                && Objects.equals(brand, tv.brand) && Objects.equals(model, tv.model)
                && Objects.equals(color, tv.color) && Objects.equals(date, tv.date)
                && Objects.equals(localDateTime, tv.localDateTime) && Objects.equals(size, tv.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, timeExpectancy, price, date, localDateTime, size);
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return "TV{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", timeExpectancy=" + timeExpectancy +
                ", price=" + price +
                //TODO использование форматтеров для дат
                ", date=" + simpleDateFormat.format(date) +
                ", localDateTime=" + dateTimeFormatter.format(localDateTime) +
                ", size=" + size +
                '}';
    }
}
