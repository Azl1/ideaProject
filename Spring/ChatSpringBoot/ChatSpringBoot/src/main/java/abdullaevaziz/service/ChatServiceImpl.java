package abdullaevaziz.service;

import abdullaevaziz.model.Message;
import abdullaevaziz.model.User;
import abdullaevaziz.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private MessageRepository messageRepository;
    private SseEmitters sseEmitters;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setSseEmitter(SseEmitters sseEmitters){
        this.sseEmitters = sseEmitters;
    }


    @Override
    public Message sendMessage(long userId, Message message) {
        User getUser = this.userService.get(userId);
        Message savedMessage = null;
        try {

            message.setUser(getUser);
           savedMessage = this.messageRepository.save(message);
            sseEmitters.send(savedMessage);
        } catch (DataIntegrityViolationException e){
            logger.error("Failed to save message from user {}: {}", userId, e.getMessage());
        }
        return savedMessage;
    }


    @Override
    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }
}
