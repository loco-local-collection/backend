package com.loco.platform.service;

import com.loco.platform.domain.Locations;
import com.loco.platform.domain.Places;
import com.loco.platform.dto.PlacesRequestDto;
import com.loco.platform.dto.PlacesResponseDto;
import com.loco.platform.repository.LocationsRepository;
import com.loco.platform.repository.PlacesRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlacesService {

    private final PlacesRepository placesRepository;
    private final LocationsRepository locationsRepository;

    @Transactional
    public PlacesResponseDto savePlace(PlacesRequestDto placesRequestDto) {

        Locations location = placesRequestDto.getLocation().toEntity();
        Locations savedLocation = locationsRepository.save(location);

        Places place = placesRequestDto.toEntity(savedLocation);
        Places savedPlace = placesRepository.save(place);

        return PlacesResponseDto.fromEntity(savedPlace);
    }

    @Transactional(readOnly = true)
    public List<PlacesResponseDto> getAllPlaces() {

        return placesRepository.findAll()
            .stream()
            .map(PlacesResponseDto::fromEntity)
            .collect(Collectors.toList());
    }
}
