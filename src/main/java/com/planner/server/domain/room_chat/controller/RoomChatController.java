package com.planner.server.domain.room_chat.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.server.domain.message.Message;
import com.planner.server.domain.room_chat.service.RoomChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/room-chat")
@RequiredArgsConstructor
public class RoomChatController {
    private final RoomChatService roomChatService;

    // study-room에서 주고받은 채팅내역 전체 조회
    @GetMapping("/all/study-room/{roomId}")
    public ResponseEntity<?> searchAll(@PathVariable UUID roomId) {
        Message message = new Message();

        try {
            message.setData(roomChatService.searchAll(roomId));
            message.setStatus(HttpStatus.OK);
            message.setMessage("success");
        } catch (Exception e) {
            message.setStatus(HttpStatus.BAD_REQUEST);
            message.setMessage("error");
            message.setMemo(e.getMessage());
        }

        return new ResponseEntity<>(message, message.getStatus());
    }
}
