package com.abdullaevaziz.model;

import java.util.Arrays;
import java.util.Objects;

public class SaleTransaction {

    private int saleCode;
    private Product[] items = new Product[3];
    private double totalCost;
    private int count;

    public SaleTransaction() {
        this.saleCode = new RandomNumberGenerator().generate(1000, 9999);
    }

    public Product[] getItems() {
        return items;
    }

    public int getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(int saleCode) {
        this.saleCode = saleCode;
    }


    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleTransaction that = (SaleTransaction) o;
        return saleCode == that.saleCode && Double.compare(that.totalCost, totalCost) == 0 && Arrays.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(saleCode, totalCost);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    @Override
    public String toString() {
        return "SaleTransaction{" +
                "saleCode=" + saleCode +
                ", items=" + Arrays.toString(items) +
                ", totalCost=" + totalCost +
                '}';
    }

    public boolean addProduct(Product product) {
        totalCost += product.getPrice() * product.getMinOrderQty();
        this.items[this.count++] = product;
        return true;

    }

    public boolean removeProduct(int index) {
        if (index >= 0 && index < count && items[index] != null) {
            totalCost -= items[index].getPrice() * items[index].getMinOrderQty();


            for (int i = index; i < count - 1; i++) {
                items[i] = items[i + 1];
            }


            items[count - 1] = null;

            count--;
            return true;
        }
        return false;
    }


    public void displayItems() {
        System.out.println("\n=== Детали продажи ===");
        System.out.println("Номер продажи: " + saleCode);
        System.out.printf("Общая стоимость: $%.2f\n", totalCost);
        System.out.println("\nТовары в корзине:");

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println("\nТовар #" + (i + 1) + ":");
                items[i].display();
            }
        }
    }
}
