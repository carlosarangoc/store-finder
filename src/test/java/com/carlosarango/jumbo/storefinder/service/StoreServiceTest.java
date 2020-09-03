package com.carlosarango.jumbo.storefinder.service;

import static com.carlosarango.jumbo.storefinder.context.TestData.store;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.carlosarango.jumbo.storefinder.domain.Store;
import com.carlosarango.jumbo.storefinder.repository.StoreRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StoreServiceTest {

  @Mock private StoreRepository storeRepository;
  @InjectMocks private StoreService storeService;

  @Test
  void should_find_nearest_stores() {
    // Given
    Double lon = 5.654948;
    Double lat = 50.863642;
    final Store store1 = store();
    final Store store2 = store("KHwKYx4XgGUAAAFI02sYwKxK", "Jumbo Nuth Markt", 5.88478, 50.9173);
    List<Store> stores =
        List.of(store1, store2, store("igwKYx4XMQwAAAFIxmgYwKxK", "Far away", -2.456, -40.809));
    when(storeRepository.findAll()).thenReturn(stores);
    // When
    List<Store> nearestStores = storeService.findStoresNearLocation(lon, lat, 2);
    // Then
    verify(storeRepository).findAll();
    assertThat(nearestStores.size(), is(2));
    assertThat(nearestStores.get(0).getUuid(), is(store1.getUuid()));
    assertThat(nearestStores.get(1).getUuid(), is(store2.getUuid()));
  }
}
