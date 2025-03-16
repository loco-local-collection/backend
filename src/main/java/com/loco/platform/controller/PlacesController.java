package com.loco.platform.controller;

import com.loco.platform.dto.PlacesRequestDto;
import com.loco.platform.dto.PlacesResponseDto;
import com.loco.platform.service.PlacesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
public class PlacesController {

  private final PlacesService placesService;

  @PostMapping
  public ResponseEntity<PlacesResponseDto> createPlace(@RequestBody PlacesRequestDto requestDto) {

    PlacesResponseDto savedPlace = placesService.savePlace(requestDto);
    return ResponseEntity.ok(savedPlace);
  }

  @GetMapping
  public ResponseEntity<List<PlacesResponseDto>> getAllPlaces() {
    return ResponseEntity.ok(placesService.getAllPlaces());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePlace(@PathVariable Long id) {

    placesService.deletePlace(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<PlacesResponseDto> updatePlace(
      @PathVariable Long id, @RequestBody PlacesRequestDto requestDto) {
    PlacesResponseDto updatedPlace = placesService.updatePlace(id, requestDto);
    return ResponseEntity.ok(updatedPlace);
  }
}
