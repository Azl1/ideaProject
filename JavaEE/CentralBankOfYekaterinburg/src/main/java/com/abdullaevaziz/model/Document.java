package com.abdullaevaziz.model;


import java.util.ArrayList;
import java.util.Objects;

/**
 * Диспетчер печати может работать с несколькими типами документов (3-5 типов).
 * Каждый тип документа должен иметь уникальные реквизиты:
 * продолжительность печати, наименование типа документа, размер бумаги.
 */


public class Document implements Comparable<Document>{

    private int printingOrder;
    private String name;
    private double printDuration;
    private String paperSize;
    private ArrayList<Document> arrayList = new ArrayList<>();


    public Document(int printingOrder, String name, double printDuration, String paperSize) {
        this.printingOrder = printingOrder;
        this.name = name;
        this.printDuration = printDuration;
        this.paperSize = paperSize;
    }

    public int getPrintingOrder() {
        return printingOrder;
    }

    public void setPrintingOrder(int printingOrder) {
        this.printingOrder = printingOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrintDuration() {
        return printDuration;
    }

    public void setPrintDuration(double printDuration) {
        this.printDuration = printDuration;
    }

    public String getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    public ArrayList<Document> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Document> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return printingOrder == document.printingOrder && Double.compare(document.printDuration, printDuration) == 0 && Objects.equals(name, document.name) && Objects.equals(paperSize, document.paperSize) && Objects.equals(arrayList, document.arrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(printingOrder, name, printDuration, paperSize, arrayList);
    }

    @Override
    public String toString() {
        return "Document{" +
                "printingOrder=" + printingOrder +
                ", name='" + name + '\'' +
                ", printDuration=" + printDuration +
                ", paperSize='" + paperSize + '\'' +
                '}';
    }

    @Override
    public int compareTo(Document o) {
        return Double.compare(this.printDuration, o.printDuration);
    }
}
