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
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.netfleet.sdk.data.BoundingBox;

import java.io.IOException;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class JacksonBoundingBoxDeserializer extends StdDeserializer<BoundingBox> {
  protected JacksonBoundingBoxDeserializer() {
    super(BoundingBox.class);
  }

  @Override
  public BoundingBox deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    BoundingBox boundingBox = new BoundingBox();

    ObjectCodec codec = parser.getCodec();
    JsonNode node = codec.readTree(parser);
    JsonNode pnode = node.get("properties");

    JsonNode idNode = pnode.path("id");
    boundingBox.setId(getOrNull(idNode.longValue(), idNode));

    JsonNode southWestLatitudeNode = pnode.path("southWestLatitude");
    boundingBox.setSouthWestLatitude(getOrNull(southWestLatitudeNode.doubleValue(), southWestLatitudeNode));

    JsonNode southWestLongitudeNode = pnode.path("southWestLongitude");
    boundingBox.setSouthWestLongitude(getOrNull(southWestLongitudeNode.doubleValue(), southWestLongitudeNode));

    JsonNode northEastLatitudeNode = pnode.path("northEastLatitude");
    boundingBox.setNorthEastLatitude(getOrNull(northEastLatitudeNode.doubleValue(), northEastLatitudeNode));

    JsonNode northEastLongitudeNode = pnode.path("northEastLongitude");
    boundingBox.setNorthEastLongitude(getOrNull(northEastLongitudeNode.doubleValue(), northEastLongitudeNode));

    return boundingBox;
  }

  private <T> T getOrNull(T object, JsonNode node) {
    return node.isNull() ? null : object;
  }
}
