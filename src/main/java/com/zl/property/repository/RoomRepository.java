package com.zl.property.repository;

import com.zl.property.model.hib.property.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository  extends CrudRepository<Room, Long> {
    List<Room> findRoomByUnitId (String roomId);
    List<Room> findRoomsByUserId(String userId);
    Room findRoomsByRoomId (String roomId);
}