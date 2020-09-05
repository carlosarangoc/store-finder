package com.carlosarango.jumbo.storefinder.dataloader;

import com.carlosarango.jumbo.storefinder.domain.Store;
import com.carlosarango.jumbo.storefinder.repository.StoreRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonDataLoader implements CommandLineRunner {

  private final ResourceLoader resourceLoader;
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
    Resource resource = resourceLoader.getResource("classpath:stores.json");
    try (InputStream inputStream = resource.getInputStream()) {
      byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
      return new String(bdata, StandardCharsets.UTF_8);
    } catch (IOException e) {
      log.error("Error reading stores.json file", e);
      throw e;
    }
  }
}
