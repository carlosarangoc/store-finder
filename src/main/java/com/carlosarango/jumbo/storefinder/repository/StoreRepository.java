package com.carlosarango.jumbo.storefinder.repository;

import com.carlosarango.jumbo.storefinder.domain.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {}
