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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.netfleet.sdk.geom.geojson.GeojsonCoordinate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class JacksonCoordinateDeserializer extends StdDeserializer<GeojsonCoordinate> {

  public JacksonCoordinateDeserializer() {
    super(GeojsonCoordinate.class);
  }

  public JacksonCoordinateDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public GeojsonCoordinate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    if (parser.isExpectedStartArrayToken()) {
      return decode(parser, context);
    }

    throw new IOException("GeojsonCoordinate.class");
  }

  protected GeojsonCoordinate decode(JsonParser parser, DeserializationContext context) throws IOException {
    GeojsonCoordinate coordinate = new GeojsonCoordinate();
    coordinate.setLongitude(decode(parser, context, false));
    coordinate.setLatitude(decode(parser, context, false));
    coordinate.setAltitude(decode(parser, context, true));

    List<Double> list = new ArrayList<>();
    while (parser.hasCurrentToken() && parser.getCurrentToken() != JsonToken.END_ARRAY) {
      double element = decode(parser, context, true);
      if (!Double.isNaN(element)) {
        list.add(element);
      }
    }

    double[] additionalElements = new double[list.size()];
    for (int i = 0; i < additionalElements.length; i++) {
      additionalElements[i] = list.get(i);
    }

    coordinate.setAdditionalElements(additionalElements);
    return coordinate;
  }

  private double decode(JsonParser parser, DeserializationContext context, boolean optional) throws IOException {
    JsonToken token = parser.nextToken();
    if (token == null) {
      if (optional)
        return Double.NaN;
      else
        throw new IOException("JacksonCoordinateDeserializer Exception, ErrorCode=100");
    } else {
      switch (token) {
        case END_ARRAY:
          if (optional) {
            return Double.NaN;
          } else {
            throw new IOException("JacksonCoordinateDeserializer Exception, ErrorCode=101");
          }
        case VALUE_STRING:
          return parser.getValueAsDouble();
        case VALUE_NUMBER_FLOAT:
          return parser.getDoubleValue();
        case VALUE_NUMBER_INT:
          return parser.getLongValue();
        default:
          throw new IOException("JacksonCoordinateDeserializer Exception, ErrorCode=102");
      }
    }
  }
}
