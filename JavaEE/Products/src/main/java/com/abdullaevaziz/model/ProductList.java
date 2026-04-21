package com.abdullaevaziz.model;

import java.util.Arrays;

public class ProductList {

    private Product[] listOfProducts = new Product[5];
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductList() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductList that = (ProductList) o;
        return Arrays.equals(listOfProducts, that.listOfProducts);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(listOfProducts);
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "listOfProducts=" + Arrays.toString(listOfProducts) +
                '}';
    }

    public boolean addProduct(Product product) {
        for (Product productAdd : this.listOfProducts) {
            if (productAdd != null && productAdd.getName().equalsIgnoreCase(product.getName())) {
                System.out.println("\n Ошибка: товар с таким названием уже существует.");
                return false;
            }
        }
        if (this.count >= this.listOfProducts.length) {
            System.out.println("Ошибка: достигнуто максимальное количество товаров.");
            return false;

        }
        listOfProducts[count++] = product;
        System.out.println("Товар успешно добавлен!");
        return true;
    }

    public boolean removeProduct(Product product) {
        for (int i = 0; i < this.listOfProducts.length; i++) {
            if (listOfProducts[i].getName().equals(product.getName())) {
                for (int j = 0; j < this.listOfProducts.length; j++) {
                    listOfProducts[j] = listOfProducts[j +1];
                }
                this.listOfProducts[listOfProducts.length-1] = null;
                System.out.println("Товар не удален!");
                return true;
            }
        }
        return false;
    }

    public Product getProductName(String name){
        for (Product productName : listOfProducts) {
            if (productName.getName().equals(name)) {
                return productName;
            }
        }
        System.out.println("Ошибка: товар '" + name + "' не найден");
        return null;
    }

    public void displayProducts() {
        for (int i = 0; i < this.listOfProducts.length; i++) {
            System.out.println((i + 1) + ". " + this.listOfProducts[i]);
        }
    }
}
