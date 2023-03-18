package com.planner.server.domain.room_chat.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.server.domain.message.Message;
import com.planner.server.domain.room_chat.dto.RoomChatReqDto;
import com.planner.server.domain.room_chat.service.RoomChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/socket/room-chat")
@RequiredArgsConstructor
public class RoomChatSocketController {
private final SimpMessagingTemplate simpMessagingTemplate;
    private final RoomChatService roomChatService;

    // /topic/study-room/{roomId}를 구독하는 유저들에게 roomChatDto전달
    @PostMapping("/study-room/{roomId}")
    public ResponseEntity<?> createOneAndSendMessage(@PathVariable UUID roomId, @RequestBody RoomChatReqDto roomChatDto) {
        Message message = new Message();

        try {
            message.setData(roomChatService.createAndGetMessage(roomId, roomChatDto));
            message.setStatus(HttpStatus.OK);
            message.setMessage("success");
        } catch (Exception e) {
            message.setStatus(HttpStatus.BAD_REQUEST);
            message.setMessage("error");
            message.setMemo(e.getMessage());
        }
        simpMessagingTemplate.convertAndSend("/topic/study-room/" + roomId, message);

        return new ResponseEntity<>(message, message.getStatus());
    }
}
