package com.loco.platform.controller;

import com.loco.platform.dto.LocationsRequestDto;
import com.loco.platform.dto.LocationsResponseDto;
import com.loco.platform.service.LocationsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LocationsController {

    private final LocationsService locationsService;

    @PostMapping
    public ResponseEntity<LocationsResponseDto> createLocation(
        @RequestBody LocationsRequestDto locationsRequestDto) {
        LocationsResponseDto savedLocation = locationsService.saveLocation(locationsRequestDto);
        return ResponseEntity.ok(savedLocation);
    }

    @GetMapping
    public ResponseEntity<List<LocationsResponseDto>> getAllLocations() {
        List<LocationsResponseDto> locations = locationsService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
}
