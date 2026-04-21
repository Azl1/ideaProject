
package com.kirillkotov.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "length",
    "width",
    "height"
})
public class Size {

    @JsonProperty("length")
    private double length;
    @JsonProperty("width")
    private double width;
    @JsonProperty("height")
    private double height;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Size() {
    }

    /**
     * 
     * @param length
     * @param width
     * @param height
     */
    public Size(double length, double width, double height) {
        super();
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @JsonProperty("length")
    public double getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(double length) {
        this.length = length;
    }

    @JsonProperty("width")
    public double getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(double width) {
        this.width = width;
    }

    @JsonProperty("height")
    public double getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(double height) {
        this.height = height;
    }

}
