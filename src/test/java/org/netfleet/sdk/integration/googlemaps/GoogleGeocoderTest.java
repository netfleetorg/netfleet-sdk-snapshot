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
package org.netfleet.sdk.integration.googlemaps;

import com.google.maps.errors.ApiException;
import org.junit.Ignore;
import org.netfleet.sdk.geom.Position;

import java.io.IOException;
import java.util.Set;

public class GoogleGeocoderTest {

  private void stdOut(Object o) {
    System.out.println("[#] " + o);
  }

  @Ignore
  public void testGeocode() throws InterruptedException, ApiException, IOException {
    GeocodeParameters parameters = new GeocodeParameters.Builder()
        .setApiKey("")
        .setAddress("Alavardı, Meram/Konya")
        .setCoordinate("37.8732096,32.4442067")
        .build();

    GoogleGeocoder geocoder = new GoogleGeocoder(parameters);
    Set<Position> positions = geocoder.geocode();
    Set<String> addresses = geocoder.reverse();

    for (Position position: positions) {
      stdOut(position.getOrdinate(0));
      stdOut(position.getOrdinate(1));
    }

    stdOut("---------------------------------------");

    for (String addr: addresses) {
      stdOut(addr);
    }
  }

}