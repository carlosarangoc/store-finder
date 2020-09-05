package com.carlosarango.jumbo.storefinder.service;

import com.carlosarango.jumbo.storefinder.domain.Store;
import com.carlosarango.jumbo.storefinder.repository.StoreRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StoreService {

  private final StoreRepository storeRepository;
  private List<Store> stores;

  public StoreService(StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  public List<Store> findStoresNearLocation(Double lon, Double lat, int size) {
    if (Objects.isNull(stores) || stores.isEmpty()) {
      // Load all stores in memory since the list is not very large.
      log.info("Loading stores from the DB");
      stores = storeRepository.findAll();
    }

    return stores.stream()
        .sorted(
            (store1, store2) -> {
              double distanceToStore1 =
                  distance(store1.getLatitude(), lat, store1.getLongitude(), lon);
              double distanceToStore2 =
                  distance(store2.getLatitude(), lat, store2.getLongitude(), lon);
              return Double.compare(distanceToStore1, distanceToStore2);
            })
        .limit(size)
        .collect(Collectors.toList());
  }

  /** From https://www.geeksforgeeks.org/program-distance-two-points-earth/ */
  private double distance(double lat1, double lat2, double lon1, double lon2) {
    lon1 = Math.toRadians(lon1);
    lon2 = Math.toRadians(lon2);
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);

    // Haversine formula
    double dlon = lon2 - lon1;
    double dlat = lat2 - lat1;
    double a =
        Math.pow(Math.sin(dlat / 2), 2)
            + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

    double c = 2 * Math.asin(Math.sqrt(a));

    // Radius of earth in kilometers. Use 3956 for miles
    double r = 6371;

    return (c * r);
  }
}
