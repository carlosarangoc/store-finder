package com.carlosarango.jumbo.storefinder.dataloader;

import com.carlosarango.jumbo.storefinder.domain.Store;
import com.carlosarango.jumbo.storefinder.repository.StoreRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonDataLoader implements CommandLineRunner {

  private final StoreRepository repository;

  @Override
  public void run(String... args) throws Exception {
    List<Store> stores = parseJson();
    repository.deleteAll();
    repository.saveAll(stores);
  }

  private List<Store> parseJson() throws IOException {
    String content = readJsonContent();
    Gson gson = new Gson();
    Type storeListType = new TypeToken<ArrayList<Store>>() {}.getType();
    return gson.fromJson(content, storeListType);
  }

  private String readJsonContent() throws IOException {
    File file = new File(getClass().getClassLoader().getResource("stores.json").getFile());
    return Files.readString(file.toPath());
  }
}
