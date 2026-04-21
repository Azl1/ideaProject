package abdullaevaziz.model;

import abdullaevaziz.repository.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;

public class LoaderService {

    private String urlSite;
    private String outputFileName;
    private String statsFileName;

    public LoaderService(String urlSite, String outputFileName, String statsFileName) {
        this.urlSite = urlSite;
        this.outputFileName = outputFileName;
        this.statsFileName = statsFileName;
    }

    /**
     * В методе load сервиса создать 2 параллельных потока.
     * Первый поток должен создавать объект репозитория и загружать
     * данные с сервера в файл, а второй поток должен раз в 10 секунд
     * записывать статистику в файл: текущую дату и время, пока первый поток не завершит свою работу
     * Сервис должен дождаться завершения всех потоков
     * и вернуть методу main результат true, который необходимо будет там вывести
     */
    public void load() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Repository repository = new Repository(urlSite, new Check());
                    repository.load(outputFileName);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (thread1.isAlive()) {
                        LocalDate localDate = LocalDate.now();
                        try (FileWriter fileWriter = new FileWriter(statsFileName, true)) {
                            fileWriter.append(localDate.toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Thread.sleep(10000);
                    }
                } catch (InterruptedException e) {
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
        }
    }

    public String getUrlSite() {
        return urlSite;
    }

    public void setUrlSite(String urlSite) {
        this.urlSite = urlSite;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public String getStatsFileName() {
        return statsFileName;
    }

    public void setStatsFileName(String statsFileName) {
        this.statsFileName = statsFileName;
    }

    @Override
    public String toString() {
        return "LoaderService{" +
                "line='" + urlSite + '\'' +
                ", outputFileName ='" + outputFileName + '\'' +
                ", statsFileName='" + statsFileName + '\'' +
                '}';
    }
}
