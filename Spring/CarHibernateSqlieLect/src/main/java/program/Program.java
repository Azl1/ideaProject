package program;

import DAO.DAO;
import model.*;

import java.util.ArrayList;
import java.util.Date;

public class Program {
    public static void main(String[] args) {
        // Создание нового человека и его привязка к аккаунту
        Person personLev = new Person("Lev", 24, 10000);
        DAO.addObject(personLev);
        System.out.println(personLev);

        Account accountLev = new Account(personLev, "lev1990", "1234", new Date());
        DAO.addObject(accountLev);
        System.out.println(accountLev);

        // Создание нескольких машин для человека и их привязка к человеку
        Car carMercedes = new Car("Mercedes", "E53", "Black", 2000, personLev);
        DAO.addObject(carMercedes);
        System.out.println(carMercedes);
        Car carDaewoo = new Car("Daewoo", "Nexia", "Gold", 10000, personLev);
        DAO.addObject(carDaewoo);
        System.out.println(carDaewoo);
        ArrayList<Car> levCars = new ArrayList<>();
        levCars.add(carMercedes);
        levCars.add(carDaewoo);
        personLev.setCars(levCars);
        DAO.updateObject(personLev);

        // Присвоение должности из ENUM
        personLev.setPosition(Position.MANAGER);
        DAO.updateObject(personLev);

        // Создание 3-х отелей. Лев был в 2-х из них.
        Hotel hotelLotte = new Hotel("LottePlaza", 4.9, 5);
        DAO.addObject(hotelLotte);
        System.out.println(hotelLotte);
        Hotel hotelMeridian = new Hotel("Meridian", 4.5, 4);
        DAO.addObject(hotelMeridian);
        System.out.println(hotelMeridian);
        Hotel hotelSindbad = new Hotel("Sindbad", 4.7, 5);
        DAO.addObject(hotelSindbad);
        System.out.println(hotelSindbad);

        personLev.addHotel(hotelMeridian);
        personLev.addHotel(hotelSindbad);
        DAO.updateObject(personLev);

        // Создание нового аккаунта Дмитрий, новой персоны, собственного списка машин
        // а также заселение в 2 отеля из трех доступных и присвоение должности из ENUM
        Person personDmitry = new Person("Dmitry", 27, 15000);
        DAO.addObject(personDmitry);
        System.out.println(personDmitry);

        Account accountDima = new Account(personDmitry, "dimon", "111", new Date());
        DAO.addObject(accountDima);
        System.out.println(accountDima);

        Car carBmw = new Car("BMW", "X5", "Black", 18000, personDmitry);
        DAO.addObject(carBmw);
        System.out.println(carBmw);

        ArrayList<Car> dmitryCars = new ArrayList<>();
        dmitryCars.add(carBmw);
        personDmitry.setCars(dmitryCars);
        personDmitry.addHotel(hotelLotte);
        personDmitry.addHotel(hotelMeridian);
        personDmitry.setPosition(Position.DEVELOPER);
        DAO.updateObject(personDmitry);

        // Заполнение логов
        accountLev.addLog();
        DAO.updateObject(accountLev);
        accountDima.addLog();
        accountDima.addLog();
        accountDima.addLog();
        DAO.updateObject(accountDima);

        // Проверим всё, что заполнилось
        System.out.println(accountDima);
        System.out.println(accountLev);

        // Получение человека по ID
        Person getPersonLev = (Person) DAO.getObjectById(1L, Person.class);
        System.out.println(getPersonLev);
        DAO.closeOpenedSession();
    }
}
