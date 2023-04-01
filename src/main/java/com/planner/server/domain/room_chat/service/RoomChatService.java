package com.planner.server.domain.room_chat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.planner.server.domain.room_chat.dto.RoomChatReqDto;
import com.planner.server.domain.room_chat.dto.RoomChatResDto;
import com.planner.server.domain.room_chat.entity.RoomChat;
import com.planner.server.utils.SecurityContextHolderUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomChatService {
    private final RedisTemplate redisTemplate;

    @Transactional
    public RoomChatResDto createAndGetMessage(UUID roomId, RoomChatReqDto roomChatDto) {
        HashOperations<String, UUID, RoomChat> hashOperations = redisTemplate.opsForHash();
        UUID userId = SecurityContextHolderUtils.getUserId();

        RoomChat roomChat = RoomChat.builder()
            .id(UUID.randomUUID())
            .content(roomChatDto.getContent())
            .studyRoomId(roomId)
            .createdAt(LocalDateTime.now())
            .userId(userId)
            .build();

        String key = "chat:" + roomId;
        hashOperations.put(key, roomChat.getId(), roomChat);
        RoomChatResDto roomChatResDto = RoomChatResDto.toDto(roomChat);
        return roomChatResDto;
    }

    public List<RoomChatResDto> searchAll(UUID roomId) throws JsonMappingException, JsonProcessingException {
        HashOperations<String, UUID, RoomChat> hashOperations = redisTemplate.opsForHash();
        String key = "chat:" + roomId;

        Map<UUID, RoomChat> chatList = hashOperations.entries(key);
        List<RoomChat> roomChat = new ArrayList<>(chatList.values());
        
        List<RoomChatResDto> roomChatResDtos = roomChat.stream().map(RoomChatResDto::toDto).collect(Collectors.toList());
        return roomChatResDtos;
    }
}
