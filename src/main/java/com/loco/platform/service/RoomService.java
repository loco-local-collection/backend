package com.loco.platform.service;

import com.loco.platform.domain.Rooms;
import com.loco.platform.dto.request.SaveRoomDto;
import com.loco.platform.repository.RoomRepository;
import com.loco.platform.repository.RoomsActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomsActivityRepository roomsActivityRepository;

    public void save(SaveRoomDto saveRoomDto) {
        roomRepository.save(requestDtoToRooms(saveRoomDto));
    }

    public void update(SaveRoomDto saveRoomDto) {
        if(!roomsActivityRepository.update(requestDtoToRooms(saveRoomDto)))
            throw new RuntimeException("update fail");
    }

    private Rooms requestDtoToRooms(SaveRoomDto saveRoomDto) {
        return Rooms.builder()
                .id(saveRoomDto.getId())
                .users(saveRoomDto.getUser())
                .name(saveRoomDto.getRoomName())
                .description(saveRoomDto.getDescription())
                .build();
    }
}
