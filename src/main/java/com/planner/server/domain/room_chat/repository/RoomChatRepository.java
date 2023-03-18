package com.planner.server.domain.room_chat.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.planner.server.domain.room_chat.entity.RoomChat;

public interface RoomChatRepository extends CrudRepository<RoomChat, UUID> {
    List<RoomChat> findByStudyRoomId(UUID studyRoomId);
}
