package com.loco.platform.service;

import com.loco.platform.domain.RoomTags;
import com.loco.platform.domain.Rooms;
import com.loco.platform.domain.Tags;
import com.loco.platform.dto.request.SaveRoomDto;
import com.loco.platform.exception.ErrorCode;
import com.loco.platform.exception.RoomException;
import com.loco.platform.repository.RoomRepository;
import com.loco.platform.repository.RoomTagsActivityRepository;
import com.loco.platform.repository.RoomsActivityRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {

    private final EntityManager entityManager;

    private final RoomRepository roomRepository;
    private final RoomsActivityRepository roomsActivityRepository;
    private final RoomTagsActivityRepository roomTagsActivityRepository;

    public List<Rooms> findByUserId(Long id) {
        return roomRepository.findByUserId(id);
    }

    @Transactional
    public void save(SaveRoomDto saveRoomDto) {
        roomRepository.save(requestDtoToRooms(saveRoomDto));
    }

    @Transactional
    public void update(SaveRoomDto saveRoomDto) {
        if(!roomsActivityRepository.update(requestDtoToRooms(saveRoomDto)))
            throw new RoomException(ErrorCode.BAD_USER_REQUEST);
    }

    @Transactional
    public void delete(Long roomId, Long userId) {
        if(!roomsActivityRepository.delete(roomId, userId))
            throw new RoomException(ErrorCode.BAD_USER_REQUEST);
    }

    private Rooms requestDtoToRooms(SaveRoomDto saveRoomDto) {
        Rooms room = Rooms.builder()
                .id(saveRoomDto.getId())
                .users(saveRoomDto.getUser())
                .name(saveRoomDto.getRoomName())
                .description(saveRoomDto.getDescription())
                .build();
        List<RoomTags> tags = findTagsByNames(saveRoomDto.getTags(), room);

        room.addTags(tags);

        return room;
    }

    private List<RoomTags> findTagsByNames(List<String> tagNames, Rooms room) {
        List<Tags> tags = roomTagsActivityRepository.findTagsByName(tagNames);

        if(tags.size() != tagNames.size())
            throw new RoomException(ErrorCode.NOT_EXIST_ROOM_TAG);

        return tags.stream()
            .map(t -> RoomTags.builder()
                .rooms(room)
                .tags(entityManager.getReference(Tags.class, t.getId()))
                .build())
            .collect(Collectors.toList());
    }
}
