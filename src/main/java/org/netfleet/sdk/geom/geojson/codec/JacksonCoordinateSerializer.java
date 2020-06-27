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
package org.netfleet.sdk.geom.geojson.codec;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.netfleet.sdk.geom.geojson.GeojsonCoordinate;

import java.io.IOException;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class JacksonCoordinateSerializer extends StdSerializer<GeojsonCoordinate> {

  public JacksonCoordinateSerializer() {
    super(GeojsonCoordinate.class);
  }

  public JacksonCoordinateSerializer(Class<GeojsonCoordinate> t) {
    super(t);
  }

  @Override
  public void serialize(GeojsonCoordinate coordinate,
                        JsonGenerator json,
                        SerializerProvider provider) throws IOException {
    json.writeStartArray();

    json.writeNumber(coordinate.getLongitude());
    json.writeNumber(coordinate.getLatitude());

    if (coordinate.hasAltitude()) {
      json.writeNumber(coordinate.getAltitude());
    }

    if (coordinate.hasAdditionalElements()) {
      for (double element : coordinate.getAdditionalElements()) {
        json.writeNumber(element);
      }
    }

    json.writeEndArray();
  }

}
