/*
 * Copyright 2017-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.google.cloud.spring.logging.JsonLoggingEventEnhancer;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Marker;

public class CustomJsonLoggingEventEnhancer implements JsonLoggingEventEnhancer {

  @Override
  public void enhanceJsonLogEntry(Map<String, Object> jsonMap, ILoggingEvent event) {
    if (!event.getMarkerList().isEmpty()) {
      // covnert the list of strings in event.getMarkerList() to Map<String, String> where the key
      // is marker1, marker2, ...
      Map<String, String> markers = new HashMap<>();
      for (int i = 0; i < event.getMarkerList().size(); i++) {
        Marker marker = event.getMarkerList().get(i);
        markers.put("marker" + (i + 1), marker.getName());
      }

      jsonMap.put("labels", markers);
    }
  }
}
