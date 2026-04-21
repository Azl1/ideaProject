package com.kirillkotov.service;

import com.kirillkotov.events.FolderChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FolderWatchService {

    private static final Logger logger = LoggerFactory.getLogger(FolderWatchService.class);

    private final ApplicationEventPublisher eventPublisher;
    private ExecutorService singleThreadExecutor;
    private WatchService watchService;

    public FolderWatchService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void start(String folderName) {
         singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            try {
                logger.info("Folder watch service started");

                this.watchService = FileSystems.getDefault().newWatchService();
                Path folder = Paths.get(folderName);
                folder.register(watchService,
                        StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE,
                        StandardWatchEventKinds.ENTRY_MODIFY);

                WatchKey key;
                while ((key = watchService.take()) != null) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();

                        if (kind == StandardWatchEventKinds.OVERFLOW) {
                            continue;
                        }

                        WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                        Path path = folder.resolve(pathEvent.context());

                        logger.info("Folder change event is published: {}", pathEvent);
                        eventPublisher.publishEvent(new FolderChangeEvent(this, pathEvent, path));
                    }

                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }

                watchService.close();
                logger.info("Folder watch service finished");
            } catch (Exception e) {
                logger.error("Folder watch service failed", e);
            }
        });
    }

    @PreDestroy
    public void onDestroy() {
        //TODO остановить выполнение потока отвечающего за просмотр папки
        try {
            if (watchService != null) {
                watchService.close();
            }

            if (singleThreadExecutor != null) {
                singleThreadExecutor.shutdownNow();
            }
            logger.info("Folder watch service finished");
        } catch (IOException e) {
            logger.error("Folder watch service failed", e);
        }
    }
}
