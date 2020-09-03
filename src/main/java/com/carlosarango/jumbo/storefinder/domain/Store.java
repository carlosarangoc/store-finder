package com.carlosarango.jumbo.storefinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Store {

  @JsonIgnore @Id private String id;
  private String uuid;
  private String city;
  private String postalCode;
  private String street;
  private String street2;
  private String street3;
  private String addressName;
  private double longitude;
  private double latitude;
  private String complexNumber;
  private Boolean showWarningMessage;
  private String todayOpen;
  private String locationType;
  private Boolean collectionPoint;
  private String sapStoreID;
  private String todayClose;
}
