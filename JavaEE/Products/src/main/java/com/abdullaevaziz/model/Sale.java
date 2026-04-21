package com.abdullaevaziz.model;

import java.util.Scanner;

public class Sale {

    private ProductList prodList = new ProductList();
    private SaleTransaction transaction = new SaleTransaction();
    private Scanner scanner = new Scanner(System.in);

    public void start(){

        boolean run = true;

        while (run){
            System.out.println("\nМеню управления запасами:");
            System.out.println("1. Зарегистрировать новый товар");
            System.out.println("2. Купить товар");
            System.out.println("3. Удалить товар из корзины");
            System.out.println("4. Просмотреть все зарегистрированные товары");
            System.out.println("5. Завершить продажу и оформить заказ");
            System.out.println("6. Справка по системе");
            System.out.println("7. Выход из системы");
            System.out.print("Выберите пункт: ");

            String input = scanner.nextLine();

            switch (input){
                case "1" -> registerProduct();
                case "2" -> purchaseProduct();
                case "3" -> removeProduct();
                case "4" -> display();
                case "5" -> completeSale();
                case "6" -> help();
                case "7" -> {
                    System.out.println("Выход из программы...");
                    run = false;
                }
                default -> System.out.println("Неверный ввод");
            }
        }
    }

    public void registerProduct(){
        try {
            System.out.print("\n=== Регистрация товара ===\nНазвание (3-25 символов): ");
            String name = scanner.next();

            if (name.length() < 3 || name.length() > 25) {
                System.out.println("Ошибка: недопустимая длина названия");
                return;
            }

            System.out.print("Описание (1-50 символов): ");
            String desc = scanner.next();

            if (desc.length() < 1 || desc.length() > 50) {
                System.out.println("Ошибка: недопустимая длина описания");
                return;
            }

            System.out.print("Цена (>0): ");
            double price = Double.parseDouble(scanner.next());

            if (price <= 0) {
                System.out.println("Ошибка: цена должна быть положительной");
                return;
            }

            Product product = new Product(name, desc, price);

            if (prodList.addProduct(product)) {
                System.out.println("\nТовар успешно зарегистрирован!");
                product.display();
            } else {
                System.out.println("Не удалось добавить товар!");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void purchaseProduct() {
        prodList.displayProducts();
        System.out.print("Введите название продукта для покупки: ");
        String name = scanner.next();
        Product productGet = prodList.getProductName(name);
        if (productGet == null || productGet.getQtyOnHand() < productGet.getMinOrderQty()) {
            System.out.println("Товар недоступен или его недостаточно.");
            return;
        }
        if (!transaction.addProduct(productGet)) {
            System.out.println("Достигнут лимит покупок.");
        } else {
            System.out.println("Товар добавлен в продажу.");
        }
    }

    public void removeProduct(){
        Product[] items = transaction.getItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println(i + ": " + items[i].getName());
            }
        }
        System.out.print("Введите индекс для удаления: ");
        int index = Integer.parseInt(scanner.next());
        if (transaction.removeProduct(index)) {
            System.out.println("Удален.");
        } else {
            System.out.println("Неверный индекс.");
        }
    }

    public void display(){
        transaction.displayItems();
    }

    public void completeSale() {
        for (Product p : transaction.getItems()) {
            if (p != null) {
                if (p.getQtyOnHand() >= p.getMinOrderQty()) {
                    p.setQtyOnHand(p.getQtyOnHand() - p.getMinOrderQty());
                } else {
                    System.out.println("Недостаточно запасов для: " + p.getName());
                }
            }
        }
        transaction.displayItems();
    }



    public void help(){
        System.out.println("Опция 1 – Регистрация нового товара (От 3 до 25 символов и не должно повторяться), описание товара " +
                "(От 1 до 50 символов)");
        System.out.println("Опция 2 – Покупка товара (Можно покупать только зарегистрированные товары)");
        System.out.println("Опция 3 – Удаление купленного товара (Удалять можно только ранее купленные товары)");
        System.out.println("Опция 4 – Просмотр всех зарегистрированных товаров ()");
        System.out.println("Опция 5 – Финализация покупки (Проверка: есть ли купленные товары)");
        System.out.println("Опция 6 – Справка (показывает, как использовать систему)");
        System.out.println("Опция 7 – Выход из программы");
    }

    public static void main(String[] args) {
        new Sale().start();
    }
}
