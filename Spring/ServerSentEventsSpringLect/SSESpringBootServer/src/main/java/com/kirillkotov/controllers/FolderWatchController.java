package com.kirillkotov.controllers;

import com.kirillkotov.events.FolderChangeEvent;
import com.kirillkotov.service.FolderWatchService;
import com.kirillkotov.service.SseEmitters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/sse")
public class FolderWatchController implements ApplicationListener<FolderChangeEvent> {
    private final FolderWatchService folderWatchService;
    private final SseEmitters emitters;

    //TODO сделать чтобы спринг сам создавал данный объект
    @Autowired
    public FolderWatchController(FolderWatchService folderWatchService, SseEmitters sseEmitters) {
        this.folderWatchService = folderWatchService;
        this.emitters = sseEmitters;
    }

    @Value("${folder.watch.path}")
    private String watchFolderPath;

    @PostConstruct
    void init() {
        //TODO вынести путь к папке в конфигурационный файл и использовать здесь
        folderWatchService.start(watchFolderPath);

    }

    @GetMapping(path = "/folder-watch", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getFolderWatch() {
        return emitters.add(new SseEmitter(60000L));
    }

    @Override
    public void onApplicationEvent(FolderChangeEvent event) {
        emitters.send(event.getEvent());
    }
}
