package com.loco.platform.contoller;

import com.loco.platform.domain.Users;
import com.loco.platform.service.RoomService;
import com.loco.platform.dto.request.SaveRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody SaveRoomDto saveRoomDto) {
        Users user = null; // get from security context
        saveRoomDto.setUser(user);

        roomService.save(saveRoomDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("success add room");
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<?> updateRoom(@RequestBody SaveRoomDto saveRoomDto, @PathVariable Long roomId) {
        saveRoomDto.setId(roomId);

        roomService.update(saveRoomDto);

        return ResponseEntity.status(HttpStatus.OK).body("success update room");
    }
}
