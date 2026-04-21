package com.util;

import java.nio.file.*;

public class FolderWatcher implements Runnable{

    private Path folder;
    public FolderWatcher(String folderPath) {
        this.folder = Paths.get(folderPath);
    }

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
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

                    System.out.println("Folder " + pathEvent.kind() + " event is published: " + path);
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

            watchService.close();
            System.out.println("Folder watch service finished");
        } catch (Exception ignored) {
        }
    }
}
