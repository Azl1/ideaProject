package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Document;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


public class PrintDispatcherService {

    /**
     * Диспетчер помещает в очередь печати неограниченное количество документов.
     * При этом каждый документ может быть обработан,
     * только если в это же время не обрабатывается другой документ,
     * время обработки каждого документа равно продолжительности печати данного документа.
     */
    private LinkedBlockingQueue<Document> linkedBlockingQueue = new LinkedBlockingQueue<>();
    private boolean flag = true;
    private ArrayList<Document> documentArrayList = new ArrayList<>();


    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (flag) {
                    Document document = linkedBlockingQueue.take();
                    ArrayList<Document> arrayList = document.getArrayList();
                    int countDocumentList = arrayList.size();
                    if (countDocumentList > 0) {
                        ExecutorService executorService =
                                Executors.newFixedThreadPool(countDocumentList);
                        for (Document document1 : arrayList) {
                            executorService.submit(() -> {
                                printDocument(document1);
                            });
                        }
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    });


    public PrintDispatcherService() {
        thread.start();
    }

    /**
     * Диспетчер должен иметь следующие методы:
     */

    /**
     * Остановка диспетчера. Печать документов в очереди отменяется.
     * На выходе должен быть список ненапечатанных документов.
     */
    public ArrayList<Document> stopPrintDocuments() {
        flag = false;
        thread.interrupt();
        return documentArrayList;
    }

    /**
     * Принять документ на печать. Метод не должен блокировать выполнение программы.
     */
    public void printDocument(Document document) {
        try {
            Thread.sleep((long) (document.getPrintDuration() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Отменить печать принятого документа, если он еще не был напечатан.
     */
    public ArrayList<Document> stopAcceptedDocument(Document document) {
        ArrayList<Document> stopDocumentArrayList = new ArrayList<>(this.linkedBlockingQueue);
        this.linkedBlockingQueue.clear();
        return stopDocumentArrayList;
    }

    /**
     * Получить отсортированный список напечатанных документов.
     * Список может быть отсортирован на выбор:
     * по порядку печати, по типу документов, по продолжительности печати, по размеру бумаги.
     */
    public ArrayList<Document> sortDocumentList(String sort) {
        //System.out.println(this.documentArrayList);
        ArrayList<Document> sortDocumentArrayList = new ArrayList<>(this.documentArrayList);
        switch (sort) {
            case "Duration":
                sortDocumentArrayList.sort(Comparator.comparing(Document::getPrintDuration));
                break;
            case "Name":
                sortDocumentArrayList.sort(Comparator.comparing(Document::getName));
                break;
            case "Size":
                sortDocumentArrayList.sort(Comparator.comparing(Document::getPaperSize));
                break;
            case "Printing order":
                sortDocumentArrayList.sort(Comparator.comparing(Document::getPrintingOrder));
                break;
            default:
                break;
        }
       /* ArrayList<String> sortedDocumentStrings = new ArrayList<>();
        for (Document doc : sortDocumentArrayList) {
            sortedDocumentStrings.add(doc.toString()); // Предполагается, что метод toString() вернет нужное строковое представление документа
        }*/
        //System.out.println(sortedDocumentStrings);
        return sortDocumentArrayList;
    }

    /**
     * Рассчитать среднюю продолжительность печати напечатанных документов
     */
    public double calculateAveragePrintTime(double value) {
        double sum = 0.0;
        for (Document document1 : documentArrayList) {
            sum += document1.getPrintDuration() + value;
        }
        return sum / documentArrayList.size();

    }


    public void addQueue(Document document) {
        this.linkedBlockingQueue.add(document);

    }

    public void addPrintList(Document document) {
        this.documentArrayList.add(document);
    }

}
