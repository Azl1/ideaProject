package com.abdullaevaziz.model;

public class SaleTransaction {
    private int saleCode;
    private Product[] items = new Product[3];
    private double totalCost = 0;
    private int count = 0;

    public SaleTransaction() {
        this.saleCode = new RandomNumberGenerator(1000, 9999).generate();
    }

    public boolean addProduct(Product p) {
        if (count < items.length) {
            items[count++] = p;
            totalCost += p.getPrice() * p.getMinOrderQuantity();
            return true;
        }
        return false;
    }

    public boolean removeProduct(int index) {
        if (index >= 0 && index < count && items[index] != null) {
            totalCost -= items[index].getPrice() * items[index].getMinOrderQuantity();
            items[index] = null;
            return true;
        }
        return false;
    }

    public Product[] getItems() {
        return items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void display() {
        System.out.println("Sale #" + saleCode + " Total Cost: $" + totalCost);
        for (Product p : items) {
            if (p != null) p.display();
        }
    }
}