package com.planner.server.domain.room_chat.dto;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class RoomChatReqDto {
    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private UUID roomId;
}