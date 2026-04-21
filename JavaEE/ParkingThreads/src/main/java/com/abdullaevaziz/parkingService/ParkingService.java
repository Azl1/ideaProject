package com.abdullaevaziz.parkingService;

import com.abdullaevaziz.model.Auto;
import com.abdullaevaziz.util.Util;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingService {

    private int countParkingSpaces; //количество парковочных мест
    private int maximumLengthQueueCars; //максимальную длину очереди автомобилей ожидающих въезда на парковку
    private long inIntervalCarSeconds; //интервал генерации входящих автомобилей в секундах
    private long outIntervalCarSeconds; //интервал генерации выходящих автомобилей в секундах
    private List<Auto> autosInParking = new CopyOnWriteArrayList<>(); //машины которые уже на парковке потокобезопасный список
    private ConcurrentLinkedQueue<Auto> queue = new ConcurrentLinkedQueue<>(); //очередь машин которые хотят заехать на парковку
    private int countCars;
    private int countTrucks;
    private Thread addToQueue;
    private Thread threadRemoveFromParking;
    private Thread threadStat;


    public ParkingService() {
    }

    public ParkingService(int countParkingSpaces,
                          int maximumLengthQueueCars,
                          long inIntervalCarSeconds,
                          long outIntervalCarSeconds) {
        this.countParkingSpaces = countParkingSpaces;
        this.maximumLengthQueueCars = maximumLengthQueueCars;
        this.inIntervalCarSeconds = inIntervalCarSeconds;
        this.outIntervalCarSeconds = outIntervalCarSeconds;
    }

    public void start() {
         addToQueue = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int min = 1, max = 2;
                        int randomNum = Util.generateRandom(min, max);
                        Auto auto = new Auto(randomNum);
                        if (randomNum == 1) {
                            countCars++;
                            System.out.println("«Легковой автомобиль с id = <"
                                    + auto.getId() + "> встал в очередь на въезд.»");
                        } else if (randomNum == 2) {
                            countTrucks++;
                            System.out.println("«Грузовой автомобиль с id = <"
                                    + auto.getId() + "> встал в очередь на въезд.»");
                        }
                        queue.add(new Auto(randomNum));
                        if (maximumLengthQueueCars == queue.size()) {
                            System.out.println("Происходит carmageddon и парковка завершает работу!!!");
                            addToQueue.interrupt();
                            threadRemoveFromParking.interrupt();
                            threadStat.interrupt();

                            return;
                        }
                        Thread.sleep(inIntervalCarSeconds * 1000);
                        toParking();
                    }
                } catch (InterruptedException ignored) {
                }
            }
        });
        addToQueue.start();


         threadRemoveFromParking = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (!autosInParking.isEmpty()) {
                            int randomNum = Util.generateRandom(1, autosInParking.size());
                            Auto autoRemove = autosInParking.remove(randomNum - 1);
                            System.out.println(autoRemove + " покинул парковку.");
                            Auto auto = new Auto(randomNum);
                            if (auto.getType() == 1) {
                                countCars--;
                            } else if (auto.getType() == 2) {
                                countTrucks--;
                            }
                            toParking();
                            Thread.sleep(outIntervalCarSeconds * 1000);
                        }
                    }
                } catch (InterruptedException ignored) {
                }
            }
        });
        threadRemoveFromParking.start();


         threadStat = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(5000);
                        int freeSpaces = countParkingSpaces - (countCars  + 2 * countTrucks);
                        int totalCars = countCars  + countTrucks;
                        System.out.println("Свободных мест: " + freeSpaces);
                        System.out.println("Занято мест: " + totalCars + "(из них легковых "
                                + countCars +" " + " и грузовых " + countTrucks+ " авто)");
                        System.out.println("Автомобилей, ожидающих в очереди: " + queue.size());
                    }
                } catch (InterruptedException ignored) {
                }
            }
        });
        threadStat.start();
    }

    private synchronized void toParking() {
        if ((countCars + 2 * countTrucks) < countParkingSpaces && !queue.isEmpty()) {
            Auto auto1 = queue.poll();
            autosInParking.add(auto1);
            if (auto1.getType() == 1) {
                countCars++;
            } else if (auto1.getType() == 2) {
                countTrucks++;
            }
        }

    }

}
