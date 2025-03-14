package com.loco.platform.dto;

import com.loco.platform.domain.Locations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationsResponseDto {

    private Long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public static LocationsResponseDto fromEntity(Locations locations) {
        return new LocationsResponseDto(
            locations.getId(),
            locations.getName(),
            locations.getAddress(),
            locations.getLatitude(),
            locations.getLongitude()
        );
    }
}
