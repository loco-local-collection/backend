package com.loco.platform.service;

import com.loco.platform.domain.Locations;
import com.loco.platform.dto.LocationsRequestDto;
import com.loco.platform.dto.LocationsResponseDto;
import com.loco.platform.repository.LocationsRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationsService {

  private final LocationsRepository locationsRepository;

  @Transactional
  public LocationsResponseDto saveLocation(LocationsRequestDto locationsRequestDto) {
    Locations location = locationsRequestDto.toEntity();
    Locations savedLocation = locationsRepository.save(location);
    return LocationsResponseDto.fromEntity(savedLocation);
  }

  @Transactional(readOnly = true)
  public List<LocationsResponseDto> getAllLocations() {
    return locationsRepository.findAll().stream()
        .map(LocationsResponseDto::fromEntity)
        .collect(Collectors.toList());
  }
}
