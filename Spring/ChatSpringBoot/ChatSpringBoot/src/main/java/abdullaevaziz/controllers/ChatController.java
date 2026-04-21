package abdullaevaziz.controllers;

import abdullaevaziz.dto.ResponseResult;
import abdullaevaziz.model.Message;
import abdullaevaziz.service.ChatService;
import abdullaevaziz.service.SseEmitters;
import abdullaevaziz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Set;

@RestController
public class ChatController {

    private ChatService chatService;

    private SseEmitters sseEmitters;

    @Autowired
    public void setChatService(ChatService chatService){
        this.chatService = chatService;
    }

    @Autowired
    public void setSseEmitters(SseEmitters sseEmitters){
        this.sseEmitters = sseEmitters;
    }



    @GetMapping(value = "/sse/chat/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable Long id) {

        SseEmitter emitter = new SseEmitter(0L);
        return sseEmitters.add(id, emitter);
    }


    @PostMapping(path = "/message/{id}")
    public ResponseEntity<ResponseResult<Message>> sendMessage(@PathVariable Long id,
                                                               @RequestBody Message message) {
        try {
            Message sendMessage = this.chatService.sendMessage(id, message);
            return new ResponseEntity<>(new ResponseResult<>(null, sendMessage), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listMessage")
    public ResponseEntity<ResponseResult<List<Message>>> getListMessage() {
        List<Message> list = this.chatService.getAllMessages();
        return new ResponseEntity<>(new ResponseResult<>(null, list),
                HttpStatus.OK);
    }

    @GetMapping("/online")
    public ResponseEntity<ResponseResult<Set<Long>>> getOnlineUsers() {
        Set<Long> set = this.sseEmitters.getOnlineUsers();
        return new ResponseEntity<>(new ResponseResult<>(null, set),
                HttpStatus.OK);

    }
}
