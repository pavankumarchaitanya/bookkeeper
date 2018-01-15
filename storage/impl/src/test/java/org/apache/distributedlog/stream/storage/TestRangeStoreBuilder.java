/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.distributedlog.stream.storage;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.apache.distributedlog.stream.storage.api.RangeStore;
import org.apache.distributedlog.stream.storage.api.sc.StorageContainerManagerFactory;
import org.apache.distributedlog.stream.storage.conf.StorageConfiguration;
import org.apache.distributedlog.stream.storage.impl.RangeStoreImpl;
import org.apache.distributedlog.stream.storage.impl.store.RangeStoreFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link RangeStoreBuilder}.
 */
public class TestRangeStoreBuilder {

  private RangeStoreFactory storeFactory;

  @Before
  public void setup() {
    this.storeFactory = mock(RangeStoreFactory.class);
  }

  @Test(expected = NullPointerException.class)
  public void testBuildNullConfiguration() {
    RangeStoreBuilder.newBuilder()
      .withStorageConfiguration(null)
      .withStorageContainerManagerFactory(mock(StorageContainerManagerFactory.class))
      .withStorageResources(StorageResources.create())
      .withRangeStoreFactory(storeFactory)
      .build();
  }

  @Test(expected = NullPointerException.class)
  public void testBuildNullResources() {
    RangeStoreBuilder.newBuilder()
      .withStorageConfiguration(mock(StorageConfiguration.class))
      .withStorageContainerManagerFactory(mock(StorageContainerManagerFactory.class))
      .withStorageResources(null)
      .withRangeStoreFactory(storeFactory)
      .build();
  }

  @Test(expected = NullPointerException.class)
  public void testBuildNullRGManagerFactory() {
    RangeStoreBuilder.newBuilder()
      .withStorageConfiguration(mock(StorageConfiguration.class))
      .withStorageContainerManagerFactory(null)
      .withStorageResources(StorageResources.create())
      .withRangeStoreFactory(storeFactory)
      .build();
  }

  @Test(expected = NullPointerException.class)
  public void testBuildNullStoreFactory() {
    RangeStoreBuilder.newBuilder()
      .withStorageConfiguration(mock(StorageConfiguration.class))
      .withStorageContainerManagerFactory(mock(StorageContainerManagerFactory.class))
      .withStorageResources(StorageResources.create())
      .withRangeStoreFactory(null)
      .build();
  }

  @Test
  public void testBuild() {
    RangeStore rangeStore = RangeStoreBuilder.newBuilder()
      .withStorageConfiguration(mock(StorageConfiguration.class))
      .withStorageContainerManagerFactory(mock(StorageContainerManagerFactory.class))
      .withStorageResources(StorageResources.create())
      .withRangeStoreFactory(storeFactory)
      .build();
    assertTrue(rangeStore instanceof RangeStoreImpl);
  }

}
