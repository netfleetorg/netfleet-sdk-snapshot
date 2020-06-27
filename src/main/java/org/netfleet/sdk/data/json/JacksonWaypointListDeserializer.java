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
import org.netfleet.sdk.data.Waypoint;
import org.netfleet.sdk.data.WaypointList;

import java.io.IOException;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class JacksonWaypointListDeserializer extends StdDeserializer<WaypointList> {

  public JacksonWaypointListDeserializer() {
    super(WaypointList.class);
  }

  @Override
  public WaypointList deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    WaypointList waypoints = new WaypointList();

    ObjectCodec codec = parser.getCodec();
    JsonNode node = codec.readTree(parser);
    JsonNode features = node.get("features");

    ObjectMapper objectMapper = new ObjectMapper();
    for (JsonNode feature: features) {
      String featureJson = feature.toString();

      Waypoint waypoint = objectMapper.readValue(featureJson, Waypoint.class);
      waypoints.addWaypoint(waypoint);
    }

    return waypoints;
  }

}
