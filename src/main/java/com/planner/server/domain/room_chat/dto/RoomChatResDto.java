package com.planner.server.domain.room_chat.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.planner.server.domain.room_chat.entity.RoomChat;

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
public class RoomChatResDto {
    private UUID id;
    private String content;
    private UUID roomId;
    private LocalDateTime createdAt;
    private UUID userId;

    public static RoomChatResDto toDto(RoomChat entity) {
        RoomChatResDto dto = RoomChatResDto.builder()
            .id(entity.getId())
            .content(entity.getContent())
            .roomId(entity.getStudyRoomId())
            .createdAt(entity.getCreatedAt())
            .userId(entity.getUserId())
            .build();

        return dto;
    }
}
