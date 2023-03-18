package com.planner.server.domain.room_chat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planner.server.domain.room_chat.dto.RoomChatReqDto;
import com.planner.server.domain.room_chat.dto.RoomChatResDto;
import com.planner.server.domain.room_chat.entity.RoomChat;
import com.planner.server.domain.room_chat.repository.RoomChatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomChatService {
    private final RoomChatRepository roomChatRepository;

    public RoomChatResDto createAndGetMessage(UUID roomId, RoomChatReqDto roomChatDto) {
        RoomChat roomChat = RoomChat.builder()
            .id(UUID.randomUUID())
            .content(roomChatDto.getContent())
            .studyRoomId(roomId)
            .createdAt(LocalDateTime.now())
            .build();

        roomChatRepository.save(roomChat);
        
        RoomChatResDto roomChatResDto = RoomChatResDto.toDto(roomChat);
        return roomChatResDto;
    }
    
    public List<RoomChatResDto> searchAll(UUID roomId) {
        List<RoomChat> roomChats = roomChatRepository.findByStudyRoomId(roomId);
        List<RoomChatResDto> roomChatResDtos = roomChats.stream().map(RoomChatResDto::toDto).collect(Collectors.toList());
        return roomChatResDtos;
    }
}
