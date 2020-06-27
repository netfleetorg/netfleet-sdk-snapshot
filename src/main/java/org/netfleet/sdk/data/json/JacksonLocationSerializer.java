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
import org.netfleet.sdk.data.Location;
import org.netfleet.sdk.geom.geojson.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class JacksonLocationSerializer extends StdSerializer<Location> {
  public JacksonLocationSerializer() {
    super(Location.class);
  }

  @Override
  public void serialize(Location location, JsonGenerator generator, SerializerProvider provider) throws IOException {
    GeojsonFeature feature = new GeojsonFeature();
    feature.setProperties(new HashMap<String, Object>());

    Field[] fields = Location.class.getDeclaredFields();
    for (Field field: fields) {
      String name = field.getName();
      Object value;
      try {
        field.setAccessible(true);
        value = field.get(location);
      } catch (IllegalAccessException e) {
        throw new IllegalStateException();
      }

      feature.setProperty(name, value);
    }

    if (location.getId() != null) {
      feature.setId(location.getId().toString());
    }

    String[] array = location.getCoordinate().split(",");
    double lat = Double.valueOf(array[0]);
    double lng = Double.valueOf(array[1]);

    GeojsonCoordinate coordinate = new GeojsonCoordinate(lat, lng);
    GeojsonPoint point = new GeojsonPoint(coordinate);
    if (location.getCrsId() != null) {
      GeojsonCrs crs = new GeojsonCrs();
      crs.setType("name");

      GeojsonCrsProperties crsProperties = new GeojsonCrsProperties();
      crsProperties.setName(location.getCrsId().toUrn());

      crs.setProperties(crsProperties);
      point.setCrs(crs);
    }

    feature.setGeometry(point);
    generator.writeObject(feature);
  }
}
