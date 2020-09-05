package com.carlosarango.jumbo.storefinder.dataloader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.carlosarango.jumbo.storefinder.domain.Store;
import com.carlosarango.jumbo.storefinder.repository.StoreRepository;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JsonDataLoaderTest {

  private static final String JSON =
      "[{\n"
          + "    \"city\": \"'s Gravendeel\",\n"
          + "    \"postalCode\": \"3295 BD\",\n"
          + "    \"street\": \"Kerkstraat\",\n"
          + "    \"street2\": \"37\",\n"
          + "    \"street3\": \"\",\n"
          + "    \"addressName\": \"Jumbo 's Gravendeel Gravendeel Centrum\",\n"
          + "    \"uuid\": \"EOgKYx4XFiQAAAFJa_YYZ4At\",\n"
          + "    \"longitude\": \"4.615551\",\n"
          + "    \"latitude\": \"51.778461\",\n"
          + "    \"complexNumber\": \"33249\",\n"
          + "    \"showWarningMessage\": true,\n"
          + "    \"todayOpen\": \"08:00\",\n"
          + "    \"locationType\": \"SupermarktPuP\",\n"
          + "    \"collectionPoint\": true,\n"
          + "    \"sapStoreID\": \"3605\",\n"
          + "    \"todayClose\": \"20:00\"\n"
          + "  }]";

  @Mock private ResourceLoader resourceLoader;
  @Mock private StoreRepository repository;
  @Captor private ArgumentCaptor<List<Store>> parametersCaptor;
  @InjectMocks private JsonDataLoader dataLoader;

  @Test
  void should_load_json_data() throws Exception {
    InputStream inputStream = new ByteArrayInputStream(JSON.getBytes());
    Resource resource = mock(Resource.class);
    when(resourceLoader.getResource("classpath:stores.json")).thenReturn(resource);
    when(resource.getInputStream()).thenReturn(inputStream);
    // When
    dataLoader.run("");
    // Then
    verify(repository).deleteAll();
    verify(repository).saveAll(parametersCaptor.capture());
    List<Store> stores = parametersCaptor.getValue();
    assertThat(stores.size(), is(1));
    assertThat(stores.get(0).getUuid(), is("EOgKYx4XFiQAAAFJa_YYZ4At"));
  }

  @Test
  void should_fail_to_load_json_data_if_file_not_found() throws Exception {
    Resource resource = mock(Resource.class);
    when(resourceLoader.getResource("classpath:stores.json")).thenReturn(resource);
    when(resource.getInputStream()).thenThrow(new IOException("File not found"));
    // When
    Assertions.assertThrows(IOException.class, () -> dataLoader.run(""));
    // Then
    verify(repository, never()).deleteAll();
    verify(repository, never()).saveAll(any());
  }
}
