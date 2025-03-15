package com.loco.platform.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRoomDto {

    private Long id;

    private String name;

    private String description;

    private List<String> tags;

    private String shareLink;

    @Builder
    public ResponseRoomDto(Long id, String name, String description, List<String> tags,
        String shareLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.shareLink = shareLink;
    }

}
