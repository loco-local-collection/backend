package com.loco.platform.dto;

import com.loco.platform.domain.Places;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlacesResponseDto {

  private Long id;
  private LocationsResponseDto location;

  public static PlacesResponseDto fromEntity(Places place) {
    return new PlacesResponseDto(
        place.getId(), LocationsResponseDto.fromEntity(place.getLocations()));
  }
}
