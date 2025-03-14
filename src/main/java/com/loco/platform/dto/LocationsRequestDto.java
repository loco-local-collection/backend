package com.loco.platform.dto;

import com.loco.platform.domain.Locations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationsRequestDto {

    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public Locations toEntity() {
        return Locations.builder()
            .name(name)
            .address(address)
            .latitude(latitude)
            .longitude(longitude)
            .build();
    }

}
