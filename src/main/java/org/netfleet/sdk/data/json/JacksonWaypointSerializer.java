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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.netfleet.sdk.data.Waypoint;
import org.netfleet.sdk.geom.geojson.GeojsonCoordinate;
import org.netfleet.sdk.geom.geojson.GeojsonFeature;
import org.netfleet.sdk.geom.geojson.GeojsonPoint;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class JacksonWaypointSerializer extends StdSerializer<Waypoint> {

  public JacksonWaypointSerializer() {
    super(Waypoint.class);
  }

  @Override
  public void serialize(Waypoint waypoint, JsonGenerator gen, SerializerProvider provider) throws IOException {
    GeojsonFeature feature = new GeojsonFeature();
    if (waypoint.getId() != null)
      feature.setId(String.valueOf(waypoint.getId()));

    if (feature.getProperties() == null) {
      feature.setProperties(new HashMap<String, Object>());
    }

    feature.setProperty("id", waypoint.getId());
    feature.setProperty("title", waypoint.getTitle());
    feature.setProperty("description", waypoint.getDescription());
    feature.setProperty("coordinate", waypoint.getCoordinate());
    feature.setProperty("unit", waypoint.getTimeUnit());
    feature.setProperty("duration", waypoint.getDuration());
    feature.setProperty("index", waypoint.getIndex());
    feature.setProperty("frequency", waypoint.getFrequency());

    if (waypoint.getCoordinate() == null) {
      throw new IllegalStateException("coordinate property cannot be null.");
    }

    String[] array = waypoint.getCoordinate().split(",");
    double lat = Double.parseDouble(array[0]);
    double lng = Double.parseDouble(array[1]);

    GeojsonCoordinate coordinate = new GeojsonCoordinate(lat, lng);
    GeojsonPoint point = new GeojsonPoint(coordinate);

    feature.setGeometry(point);
    gen.writeObject(feature);
  }
}
