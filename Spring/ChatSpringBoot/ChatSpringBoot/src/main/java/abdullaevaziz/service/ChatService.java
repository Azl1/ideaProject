package abdullaevaziz.service;


import abdullaevaziz.model.Message;

import java.util.List;

public interface ChatService {

    Message sendMessage(long userId, Message message);

    List<Message> getAllMessages();

}
