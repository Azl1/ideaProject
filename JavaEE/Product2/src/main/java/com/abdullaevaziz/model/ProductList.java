package com.abdullaevaziz.model;

public class ProductList {
    private Product[] listOfProducts = new Product[5];
    private int count = 0;

    public boolean addProduct(Product product) {
        if (count < listOfProducts.length) {
            listOfProducts[count++] = product;
            return true;
        }
        return false;
    }

    public Product[] getProducts() {
        return listOfProducts;
    }

    public Product getProductByName(String name) {
        for (Product p : listOfProducts) {
            if (p != null && p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public void display() {
        System.out.println("Registered Products:");
        for (Product p : listOfProducts) {
            if (p != null) p.display();
        }
    }
}