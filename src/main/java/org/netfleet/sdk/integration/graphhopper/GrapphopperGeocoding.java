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
package org.netfleet.sdk.integration.graphhopper;

import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.GeocodingApi;
import com.graphhopper.directions.api.client.model.GeocodingLocation;
import com.graphhopper.directions.api.client.model.GeocodingResponse;
import org.netfleet.sdk.util.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class GrapphopperGeocoding {

  private GeocodingApi api;
  private final String key;

  public GrapphopperGeocoding(String key) {
    this.key = key;
  }

  public List<String> geocode(GraphhopperGeocodingContext context) throws ApiException {
    final String searchWord = context.getAddress();

    if (api == null) {
      api = new GeocodingApi();
    }

    final GeocodingResponse response = api.geocodeGet(key,
      searchWord,
      context.getLocale(),
      context.getLimit(),
      false,
      "",
      context.getProvider());

    final List<GeocodingLocation> hits = response.getHits();
    if (hits == null || hits.isEmpty())
      return Collections.emptyList();

    final List<String> result = new ArrayList<>();
    for (GeocodingLocation hit: hits) {
      result.add(hit.getPoint().getLat() + "," + hit.getPoint().getLng());
    }

    return result;
  }

  public List<String> reverse(GraphhopperGeocodingContext context) throws ApiException {
    final String point = context.getCoordinate();

    if (api == null) {
      api = new GeocodingApi();
    }

    final GeocodingResponse response = api.geocodeGet(key,
      null,
      context.getLocale(),
      context.getLimit(),
      true,
      point,
      context.getProvider());

    final List<GeocodingLocation> hits = response.getHits();
    if (hits == null || hits.isEmpty())
      return Collections.emptyList();

    final List<String> result = new ArrayList<>();
    for (GeocodingLocation location: hits) {
      StringBuilder sb = new StringBuilder();
      appendIfNotEmpty(location.getName(), sb);
      appendIfNotEmpty(location.getStreet(), sb);
      appendIfNotEmpty(location.getHousenumber(), sb);
      appendIfNotEmpty(location.getPostcode(), sb);
      appendIfNotEmpty(location.getState(), sb);
      appendIfNotEmpty(location.getCity(), sb);
      appendIfNotEmpty(location.getCountry(), sb);

      result.add(sb.toString());
    }

    return result;
  }

  private void appendIfNotEmpty(String string, StringBuilder sb) {
    if (!Strings.isBlank(string))
      sb.append(string).append(Strings.SPACE);
  }
}
