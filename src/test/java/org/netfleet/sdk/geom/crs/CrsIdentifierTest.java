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
package org.netfleet.sdk.geom.crs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;

import java.io.IOException;

public class CrsIdentifierTest {

  @Ignore
  public void testSerialization() throws JsonProcessingException {
    CrsIdentifier crsId = CrsIdentifier.EPSG4326;

    ObjectMapper mapper = new ObjectMapper();
    System.out.println(mapper.writeValueAsString(crsId));
  }

  @Ignore
  public void testDeserialization() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    CrsIdentifier crsId = CrsIdentifier.EPSG4326;
    String urn = mapper.writeValueAsString(crsId);

    CrsIdentifier id = mapper.readValue(urn, CrsIdentifier.class);
    System.out.println(id.toUrn());
  }
}