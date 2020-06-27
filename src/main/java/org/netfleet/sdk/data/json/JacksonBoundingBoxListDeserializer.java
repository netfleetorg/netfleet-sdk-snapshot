/*
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 *  Copyright (C) 2020 Terra Yazılım Ltd Şti - All Rights Reserved
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Please contact Terra Yazılım, Konya Teknoloji Geliştirme Bölgesi
 *  Büyük Kayacık Mah. 101. Cad. No:2 42250 - Selçuklu Konya or visit
 *  www.terrayazilim.com.tr if you need additional information or have
 *  any questions.
 *
 */
package org.netfleet.sdk.data.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.netfleet.sdk.data.BoundingBox;
import org.netfleet.sdk.data.BoundingBoxList;

import java.io.IOException;

public class JacksonBoundingBoxListDeserializer extends StdDeserializer<BoundingBoxList> {

  public JacksonBoundingBoxListDeserializer() {
    super(BoundingBoxList.class);
  }

  @Override
  public BoundingBoxList deserialize(JsonParser p, DeserializationContext context) throws IOException {
    BoundingBoxList bboxList = new BoundingBoxList();

    ObjectCodec codec = p.getCodec();
    JsonNode node = codec.readTree(p);
    JsonNode features = node.get("features");

    ObjectMapper objectMapper = new ObjectMapper();
    for (JsonNode feature: features) {
      String featureJson = feature.toString();

      BoundingBox location = objectMapper.readValue(featureJson, BoundingBox.class);
      bboxList.addBoundingBox(location);
    }

    return bboxList;
  }
}
