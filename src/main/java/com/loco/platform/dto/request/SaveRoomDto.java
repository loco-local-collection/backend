package com.loco.platform.dto.request;

import com.loco.platform.domain.Users;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveRoomDto {

    private Long id;

    private Users user;

    private String roomName;

    private String description;

    private List<String> tags;

}
