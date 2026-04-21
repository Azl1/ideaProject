package abdullaevaziz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class SseEmitters {

    private final Map<Long, List<SseEmitter>> userEmitters = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(SseEmitters.class);


    public SseEmitter add(long userId, SseEmitter emitter) {
        List<SseEmitter> emitters = userEmitters.computeIfAbsent(userId,
                k -> new CopyOnWriteArrayList<>());
        emitters.add(emitter);
        logger.info("Emitters add: {}", emitters);

        emitter.onCompletion(() -> {
            logger.info("Emitter completed: {}", emitter);
            List<SseEmitter> list = userEmitters.get(userId);
            if (list != null) {
                list.remove(emitter);
                if (list.isEmpty()) {
                    userEmitters.remove(userId);
                }
            }
        });

        emitter.onTimeout(() -> {
            logger.info("Emitter timed out: {}", emitter);
            List<SseEmitter> list = userEmitters.get(userId);
            emitter.complete();
            if (list != null) {
                list.remove(emitter);
                if (list.isEmpty()) {
                    userEmitters.remove(userId);
                }
            }
        });
        return emitter;
    }

    public void send(Object obj) {
        logger.info("Emitters current before deleting: {}", this.userEmitters);
        List<Long> usersToRemove = new ArrayList<>();

        this.userEmitters.forEach((userId, emitters) -> {
            List<SseEmitter> failedEmitters = new ArrayList<>();

            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(obj);
                    logger.debug("Message sent to user: {}", userId);
                } catch (Exception e) {
                    logger.error("Failed to send message to user: {}", userId, e);
                    failedEmitters.add(emitter);
                }
            }

            if (!failedEmitters.isEmpty()) {
                emitters.removeAll(failedEmitters);
            }

            if (emitters.isEmpty()) {
                usersToRemove.add(userId);
            }
        });


        usersToRemove.forEach(userEmitters::remove);
        logger.info("Emitters current after: {}", this.userEmitters);
    }

    public Set<Long> getOnlineUsers() {
        return userEmitters.keySet();
    }
}
