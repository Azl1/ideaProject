
package com.kirillkotov.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "brand",
    "model",
    "color",
    "timeExpectancy",
    "price",
    "date",
    "localDateTime",
    "size"
})
public class Tv {

    @JsonProperty("brand")
    private String brand;
    @JsonProperty("model")
    private String model;
    @JsonProperty("color")
    private String color;
    @JsonProperty("timeExpectancy")
    private int timeExpectancy;
    @JsonProperty("price")
    private double price;
    @JsonProperty("date")
    private String date;
    @JsonProperty("localDateTime")
    private String localDateTime;
    @JsonProperty("size")
    private Size size;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tv() {
    }

    /**
     * 
     * @param date
     * @param localDateTime
     * @param color
     * @param size
     * @param price
     * @param model
     * @param timeExpectancy
     * @param brand
     */
    public Tv(String brand, String model, String color, int timeExpectancy, double price, String date, String localDateTime, Size size) {
        super();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.timeExpectancy = timeExpectancy;
        this.price = price;
        this.date = date;
        this.localDateTime = localDateTime;
        this.size = size;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("timeExpectancy")
    public int getTimeExpectancy() {
        return timeExpectancy;
    }

    @JsonProperty("timeExpectancy")
    public void setTimeExpectancy(int timeExpectancy) {
        this.timeExpectancy = timeExpectancy;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("localDateTime")
    public String getLocalDateTime() {
        return localDateTime;
    }

    @JsonProperty("localDateTime")
    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    @JsonProperty("size")
    public Size getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Size size) {
        this.size = size;
    }

}
