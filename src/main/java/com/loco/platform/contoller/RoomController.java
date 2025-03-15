package com.loco.platform.contoller;

import com.loco.platform.domain.RoomTags;
import com.loco.platform.domain.Rooms;
import com.loco.platform.domain.Users;
import com.loco.platform.dto.response.ResponseRoomDto;
import com.loco.platform.service.RoomService;
import com.loco.platform.dto.request.SaveRoomDto;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final EntityManager entityManager;

    private static final String SUCCESS_CREATE_ROOM = "success create room";
    private static final String SUCCESS_UPDATE_ROOM = "success update room";
    private static final String SUCCESS_DELETE_ROOM = "success delete room";

    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        Users user = getUserFromSecurityContext();

        List<Rooms> rooms = roomService.findByUserId(user.getId());

        List<ResponseRoomDto> roomDtos = rooms.stream()
                .map(r -> ResponseRoomDto.builder()
                    .id(r.getId())
                    .name(r.getName())
                    .description(r.getDescription())
                    .tags(getTagNames(r.getTags()))
                    .build())
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(roomDtos);
    }

    private List<String> getTagNames(List<RoomTags> roomTags) {
        return roomTags.stream().map(rt -> rt.getTags().getName()).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody SaveRoomDto saveRoomDto) {
        Users user = getUserFromSecurityContext();
        saveRoomDto.setUser(user);

        roomService.save(saveRoomDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESS_CREATE_ROOM);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<?> updateRoom(@RequestBody SaveRoomDto saveRoomDto, @PathVariable Long roomId) {
        saveRoomDto.setId(roomId);
        saveRoomDto.setUser(getUserFromSecurityContext());

        roomService.update(saveRoomDto);

        return ResponseEntity.status(HttpStatus.OK).body(SUCCESS_UPDATE_ROOM);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId) {
        Users user = getUserFromSecurityContext();

        roomService.delete(roomId, user.getId());

        return ResponseEntity.status(HttpStatus.OK).body(SUCCESS_DELETE_ROOM);
    }

    private Users getUserFromSecurityContext() {
        return entityManager.getReference(Users.class, 1L);
    }
}
