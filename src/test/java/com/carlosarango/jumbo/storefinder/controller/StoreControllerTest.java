package com.carlosarango.jumbo.storefinder.controller;

import static com.carlosarango.jumbo.storefinder.context.TestData.store;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import com.carlosarango.jumbo.storefinder.domain.Store;
import com.carlosarango.jumbo.storefinder.service.StoreService;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StoreControllerTest {

  @Mock private StoreService storeService;
  @InjectMocks private StoreController storeController;

  @Test
  void should_find_nearest_stores_to_a_location() {
    // Given
    final Double lon = 1d;
    final Double lat = 2d;
    final int size = 5;
    final List<Store> stores = List.of(store());
    when(storeService.findStoresNearLocation(lon, lat, size)).thenReturn(stores);
    // When
    ResponseEntity<List<Store>> response = storeController.findStoresNearLocation(lon, lat, size);
    // Then
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
    assertThat(response.getBody(), is(stores));
  }
}
