package com.carlosarango.jumbo.storefinder.context;

import com.carlosarango.jumbo.storefinder.domain.Store;

public class TestData {

  public static Store store() {
    return store("qBEKYx4XAvIAAAFIrcYYwKxK", "Jumbo Maastricht Malbergsingel", 5.654949, 50.863643);
  }

  public static Store store(String uuid, String name, double lon, double lat) {
    return Store.builder()
        .uuid(uuid)
        .city("Maastricht")
        .postalCode("6218 AV")
        .street("Malbergsingel")
        .street2("74")
        .street3("")
        .addressName(name)
        .longitude(lon)
        .latitude(lat)
        .complexNumber("32064")
        .showWarningMessage(true)
        .todayOpen("08:00")
        .locationType("SupermarktPuP")
        .collectionPoint(true)
        .sapStoreID("6533")
        .todayClose("20:00")
        .build();
  }
}
