package com.abdullaevaziz.service;

import com.abdullaevaziz.repository.MobileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;


public class MobileService {
    private final File processed;
    private File root;
    private File newData;
    private File processedData;
    private Path folder;

    public MobileService(File root) {
        this.root = root;
        this.newData = new File(root, "new_data");
        this.processedData = new File(root, "processed_data");
        processed = new File(processedData, "processed");
        processed.mkdirs();
    }

    public void start() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            folder = Paths.get(String.valueOf(newData));
            folder.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    try {
                        System.out.println(kind.name());
                        WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                        Path path = folder.resolve(pathEvent.context());

                        MobileRepository mobileRepository = new MobileRepository(path.toFile());
                        mobileRepository.save(root);

                        boolean isResult = path.toFile().renameTo(new File(processed, path.toFile().getName()));
                        if (!isResult) {
                            /**
                             * В случае, если в целевой папке
                             * уже имеются файлы с такими же названиями,
                             * то перенести файлы под новыми именами по шаблону
                             * имя_файла_номер_начиная_с_единицы.txt
                             */
                            String name = path.toFile().getName().replace(".txt", "");
                            int maxNumber = Arrays.stream(processed.listFiles()).filter(x -> x.getName().startsWith(name)).
                                    mapToInt(x -> {
                                        try {
                                            return Integer.parseInt(x.getName().
                                                    split("_")[6].replace(".txt", ""));
                                        } catch (Exception ignored) {
                                            return 0;
                                        }
                                    }).max().orElse(0) + 1;

                            String fileName = name + "_" + maxNumber + ".txt";
                            boolean isResult2 = path.toFile().renameTo(new File(processed, fileName));
                        }
                    } catch (IOException e) {

                    }

                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

            watchService.close();
            System.out.println("Folder watch service finished");
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }


}
