package com.abdullaevaziz.model2;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class BoardingData {

    private String passengerFirstName;
    private String passengerSecondName;
    private String passengerLastName;
    private String passengerSex;
    @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate passengerBirthDate;
    private String passengerDocument;
    private String bookingCode;
    private String ticketNumber;
    private String baggage;
    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate flightDate;
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime flightTime;
    private String flightNumber;
    private String codeShare;
    private String destination;

    private ArrayList<BoardingData> dataArrayList = new ArrayList<>();

    public BoardingData() {
    }

    public BoardingData(String passengerFirstName, String passengerSecondName, String passengerLastName,
                        String passengerSex, LocalDate passengerBirthDate, String passengerDocument,
                        String bookingCode, String ticketNumber, String baggage, LocalDate flightDate,
                        LocalTime flightTime, String flightNumber, String codeShare, String destination) {
        this.passengerFirstName = passengerFirstName;
        this.passengerSecondName = passengerSecondName;
        this.passengerLastName = passengerLastName;
        this.passengerSex = passengerSex;
        this.passengerBirthDate = passengerBirthDate;
        this.passengerDocument = passengerDocument;
        this.bookingCode = bookingCode;
        this.ticketNumber = ticketNumber;
        this.baggage = baggage;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
        this.flightNumber = flightNumber;
        this.codeShare = codeShare;
        this.destination = destination;

    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerSecondName() {
        return passengerSecondName;
    }

    public void setPassengerSecondName(String passengerSecondName) {
        this.passengerSecondName = passengerSecondName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getPassengerSex() {
        return passengerSex;
    }

    public void setPassengerSex(String passengerSex) {
        this.passengerSex = passengerSex;
    }

    public LocalDate getPassengerBirthDate() {
        return passengerBirthDate;
    }

    public void setPassengerBirthDate(LocalDate passengerBirthDate) {
        this.passengerBirthDate = passengerBirthDate;
    }

    public String getPassengerDocument() {
        return passengerDocument;
    }

    public void setPassengerDocument(String passengerDocument) {
        this.passengerDocument = passengerDocument;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public LocalTime getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(LocalTime flightTime) {
        this.flightTime = flightTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCodeShare() {
        return codeShare;
    }

    public void setCodeShare(String codeShare) {
        this.codeShare = codeShare;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardingData that = (BoardingData) o;
        return Objects.equals(passengerFirstName, that.passengerFirstName) &&
                Objects.equals(passengerSecondName, that.passengerSecondName)
                && Objects.equals(passengerLastName, that.passengerLastName) &&
                Objects.equals(passengerSex, that.passengerSex)
                && Objects.equals(passengerBirthDate, that.passengerBirthDate)
                && Objects.equals(passengerDocument, that.passengerDocument)
                && Objects.equals(bookingCode, that.bookingCode)
                && Objects.equals(ticketNumber, that.ticketNumber)
                && Objects.equals(baggage, that.baggage)
                && Objects.equals(flightDate, that.flightDate)
                && Objects.equals(flightTime, that.flightTime)
                && Objects.equals(flightNumber, that.flightNumber)
                && Objects.equals(codeShare, that.codeShare)
                && Objects.equals(destination, that.destination)
                && Objects.equals(dataArrayList, that.dataArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerFirstName, passengerSecondName,
                passengerLastName, passengerSex, passengerBirthDate,
                passengerDocument, bookingCode, ticketNumber, baggage,
                flightDate, flightTime, flightNumber, codeShare,
                destination, dataArrayList);
    }

    @Override
    public String toString() {
        return "BoardingData{" +
                "passengerFirstName='" + passengerFirstName + '\'' +
                ", passengerSecondName='" + passengerSecondName + '\'' +
                ", passengerLastName='" + passengerLastName + '\'' +
                ", passengerSex='" + passengerSex + '\'' +
                ", passengerBirthDate=" + passengerBirthDate +
                ", passengerDocument=" + passengerDocument +
                ", bookingCode='" + bookingCode + '\'' +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", baggage='" + baggage + '\'' +
                ", flightDate=" + flightDate +
                ", flightTime=" + flightTime +
                ", flightNumber='" + flightNumber + '\'' +
                ", codeShare='" + codeShare + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }

    public String toCSV() {
        return this.passengerFirstName + ";" + this.passengerSecondName + ";" + this.passengerLastName + ";" +
                this.passengerSex + ";" + this.passengerBirthDate + ";" + this.passengerDocument + ";" +
                this.bookingCode + ";" + this.ticketNumber + ";" + this.baggage + ";" + this.flightDate + ";" +
                this.flightTime + ";" + this.flightNumber + ";" + this.codeShare + ";" + this.destination;
    }
}
