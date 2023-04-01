package com.planner.server.domain.room_chat.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomChat implements Serializable {
    @Id

    @Type(type = "uuid-char")
    private UUID id;

    private String content;

    @Indexed
    @Type(type = "uuid-char")
    private UUID studyRoomId;

    private LocalDateTime createdAt;

    @Type(type = "uuid-char")
    private UUID userId;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private long expired = 5;
}
