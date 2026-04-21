package abdullaevaziz.events;

import abdullaevaziz.model.User;
import org.springframework.context.ApplicationEvent;

public class ChatMessageEvent extends ApplicationEvent {
    private User user;
    public ChatMessageEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
