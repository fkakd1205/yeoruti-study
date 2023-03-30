package com.planner.server.domain.room_chat.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@RedisHash(value = "chat", timeToLive = 10)
@AllArgsConstructor
@NoArgsConstructor
public class RoomChat {
    @Id
    private UUID id;
    private String content;
    @Indexed
    private UUID studyRoomId;
    private LocalDateTime createdAt;
}
