package com.carlosarango.jumbo.storefinder.controller;

import com.carlosarango.jumbo.storefinder.domain.Store;
import com.carlosarango.jumbo.storefinder.service.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;

  @GetMapping
  public ResponseEntity<List<Store>> findStoresNearLocation(
      @RequestParam Double lon,
      @RequestParam Double lat,
      @RequestParam(required = false, defaultValue = "5") int size) {
    return ResponseEntity.ok(storeService.findStoresNearLocation(lon, lat, size));
  }
}
