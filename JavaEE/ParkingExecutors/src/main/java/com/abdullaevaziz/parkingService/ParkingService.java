package com.abdullaevaziz.parkingService;

import com.abdullaevaziz.model.Auto;
import com.abdullaevaziz.util.Util;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ParkingService {

    private int countParkingSpaces; //количество парковочных мест
    private int maximumLengthQueueCars; //максимальную длину очереди автомобилей ожидающих въезда на парковку
    private long inIntervalCarSeconds; //интервал генерации входящих автомобилей в секундах
    private long outIntervalCarSeconds; //интервал генерации выходящих автомобилей в секундах
    private List<Auto> autosInParking = new CopyOnWriteArrayList<>(); //машины которые уже на парковке потокобезопасный список
    private ConcurrentLinkedQueue<Auto> queue = new ConcurrentLinkedQueue<>(); //очередь машин которые хотят заехать на парковку
    private volatile int countCars;
    private volatile int countTrucks;
    private ExecutorService executorService1;
    private ExecutorService executorService2;
    private ExecutorService executorService3;

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

    public synchronized void start() {
        executorService1 = Executors.newSingleThreadExecutor();
        executorService1.execute(new Runnable() {
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
                            stop();
                            // return;
                        }
                        Thread.sleep(inIntervalCarSeconds * 1000);
                        toParking();
                    }
                } catch (InterruptedException ignored) {
                }
            }
        });


        executorService2 = Executors.newSingleThreadExecutor();
        executorService2.execute(new Runnable() {
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


        executorService3 = Executors.newSingleThreadExecutor();
        executorService3.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(5000);
                        int freeSpaces = countParkingSpaces - (countCars + 2 * countTrucks);
                        int totalCars = countCars + countTrucks;
                        if (freeSpaces < 0) {
                            freeSpaces = 0;
                        }
                        System.out.println("Свободных мест: " + freeSpaces);
                        System.out.println("Занято мест: " + totalCars + "(из них легковых "
                                + countCars + " " + " и грузовых " + countTrucks + " авто)");
                        System.out.println("Автомобилей, ожидающих в очереди: " + queue.size());
                    }
                } catch (InterruptedException ignored) {
                }
            }
        });
    }


    public void stop() {
        executorService1.shutdownNow();
        executorService2.shutdownNow();
        executorService3.shutdownNow();
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






