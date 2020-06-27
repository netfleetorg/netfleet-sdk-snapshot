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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.netfleet.sdk.data.BoundingBox;
import org.netfleet.sdk.data.BoundingBoxList;
import org.netfleet.sdk.geom.geojson.GeojsonFeature;
import org.netfleet.sdk.geom.geojson.GeojsonFeatureCollection;

import java.io.IOException;
import java.util.ArrayList;

public class JacksonBoundingBoxListSerializer extends StdSerializer<BoundingBoxList> {

  public JacksonBoundingBoxListSerializer() {
    super(BoundingBoxList.class);
  }

  @Override
  public void serialize(BoundingBoxList bboxList, JsonGenerator gen, SerializerProvider provider) throws IOException {
    GeojsonFeatureCollection gfc = new GeojsonFeatureCollection();
    gfc.setFeatures(new ArrayList<GeojsonFeature>());

    ObjectMapper mapper = new ObjectMapper();
    if (bboxList.getBoundingBoxes() != null) {
      for (BoundingBox bbox: bboxList.getBoundingBoxes()) {
        String featureJson = mapper.writeValueAsString(bbox);
        GeojsonFeature feature = mapper.readValue(featureJson, GeojsonFeature.class);
        gfc.addFeature(feature);
      }
    }

    gen.writeObject(gfc);
  }
}
