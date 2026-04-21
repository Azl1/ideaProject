package com.abdullaevaziz.model;

import java.util.Scanner;

public class Sale {
    private ProductList prodList = new ProductList();
    private SaleTransaction transaction = new SaleTransaction();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: registerProduct(); break;
                case 2: purchaseProduct(); break;
                case 3: removeProduct(); break;
                case 4: prodList.display(); break;
                case 5: finalizeSale(); break;
                case 6: help(); break;
                case 7: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }

    private void showMenu() {
        System.out.println("\n--- Inventory Management ---");
        System.out.println("1. Register Product");
        System.out.println("2. Purchase Product");
        System.out.println("3. Remove Purchased Product");
        System.out.println("4. View Products");
        System.out.println("5. Finalize Sale");
        System.out.println("6. Help");
        System.out.println("7. Exit");
        System.out.print("Choose: ");
    }

    private void registerProduct() {
        if (prodList.getProducts()[4] != null) {
            System.out.println("Max product limit reached.");
            return;
        }
        System.out.print("Product name: ");
        String name = scanner.nextLine();
        if (prodList.getProductByName(name) != null) {
            System.out.println("Product already exists.");
            return;
        }
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        Product p = new Product(name, desc, price);
        prodList.addProduct(p);
        System.out.println("Product added.");
    }

    private void purchaseProduct() {
        prodList.display();
        System.out.print("Enter product name to purchase: ");
        String name = scanner.nextLine();
        Product p = prodList.getProductByName(name);
        if (p == null || p.getQuantityOnHand() < p.getMinOrderQuantity()) {
            System.out.println("Product not available or not enough quantity.");
            return;
        }
        if (!transaction.addProduct(p)) {
            System.out.println("Purchase limit reached.");
        } else {
            System.out.println("Product added to sale.");
        }
    }

    private void removeProduct() {
        Product[] items = transaction.getItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println(i + ": " + items[i].getName());
            }
        }
        System.out.print("Enter index to remove: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (transaction.removeProduct(index)) {
            System.out.println("Removed.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void finalizeSale() {
        for (Product p : transaction.getItems()) {
            if (p != null) {
                if (p.getQuantityOnHand() >= p.getMinOrderQuantity()) {
                    p.setQuantityOnHand(p.getQuantityOnHand() - p.getMinOrderQuantity());
                } else {
                    System.out.println("Not enough stock for: " + p.getName());
                }
            }
        }
        transaction.display();
    }

    private void help() {
        System.out.println("Help: Register, buy, view, and finalize product sales.");
    }

    public static void main(String[] args) {
        new Sale().start();
    }
}