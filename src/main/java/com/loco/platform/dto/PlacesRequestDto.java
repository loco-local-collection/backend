package com.loco.platform.dto;

import com.loco.platform.domain.Locations;
import com.loco.platform.domain.Places;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlacesRequestDto {

    private LocationsRequestDto location;

    public Places toEntity(Locations location) {
        return Places.builder()
            .locations(location)
            .build();
    }
}
